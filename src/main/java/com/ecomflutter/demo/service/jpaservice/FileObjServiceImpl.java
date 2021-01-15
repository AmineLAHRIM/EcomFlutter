package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.FileObj;
import com.ecomflutter.demo.dao.FileObjDao;
import com.ecomflutter.demo.service.FileObjService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;

@Service
public class FileObjServiceImpl implements FileObjService {

    @Autowired
    private FileObjDao fileObjDao;
    private Path rootLocation;
    ObjectMapper objectMapper = new ObjectMapper();
    private String pathStoring = "src/main/resources/files/";

    public FileObjServiceImpl() {
        File file = new File(pathStoring);
        if (!file.exists()) {
            file.mkdir();
        }
        this.rootLocation = file.toPath();
    }


    private int storeFile() {
        return 0;
    }

    @Transactional
    @Override
    public FileObj handleFileUpload(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = FilenameUtils.getExtension(filename);

        //create Foldre if not exist
        File folder = new File(pathStoring + extension + "/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        this.rootLocation = folder.toPath();
        FileObj savedFileObj = this.fileObjDao.save(new FileObj(filename, extension + "", pathStoring + extension + "/", "public", new Date()));
        savedFileObj.setName(savedFileObj.getId() + "." + extension);
        savedFileObj = this.fileObjDao.save(savedFileObj);

        filename = savedFileObj.getId() + "." + extension;
        try {

            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new Exception("Cannot store file with relative path outside current directory " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("1");
        } catch (Exception e) {
            this.fileObjDao.delete(savedFileObj);
            e.printStackTrace();
            System.out.println("handleFileUpload error ");
            return null;
        }
        return savedFileObj;
    }

    @Override
    public Resource handleFileDownload(Long id) {
        UrlResource resource = null;
        Optional<FileObj> fileObjOptional = this.fileObjDao.findById(id);
        if (fileObjOptional.isPresent()) {
            FileObj fileObj = fileObjOptional.get();
            String pathString = fileObj.getPath() + fileObj.getId() + "." + fileObj.getType();
            //String pathString = "src/main/resources/files/png/"+id+".png";
            Path path = Paths.get(pathString);
            try {
                resource = new UrlResource(path.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return resource;
    }


}
