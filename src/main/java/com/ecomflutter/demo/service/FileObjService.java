package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.FileObj;
import org.springframework.web.multipart.MultipartFile;

public interface FileObjService {


    FileObj handleFileUpload(MultipartFile file);
}
