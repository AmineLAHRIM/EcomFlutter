package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.FileObj;
import com.ecomflutter.demo.service.FileObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ecomflutter/file")
public class FileObjRest {

    @Autowired
    private FileObjService fileObjService;

    @PostMapping("/uploadfile")
    public FileObj handleFileUpload(@RequestBody MultipartFile file) {
        return this.fileObjService.handleFileUpload(file);
    }

    @GetMapping("/downloadfile/{id}")
    public Resource handleFileDownload(@PathVariable Long id) {
        return this.fileObjService.handleFileDownload(id);
    }
    /*@PostMapping("/uploadfiles")
    public List<FileObj> handleFilesUpload(@RequestBody List<MultipartFile> files) {
        return this.fileObjService.handleFilesUpload(files);
    }*/
}
