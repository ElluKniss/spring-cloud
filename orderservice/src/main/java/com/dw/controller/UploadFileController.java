package com.dw.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @PostMapping("/file")
    public void uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if(null == fileName){
            return;
        }

        File dir = new File("E:\\upload");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path filePath = dir.toPath().resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
