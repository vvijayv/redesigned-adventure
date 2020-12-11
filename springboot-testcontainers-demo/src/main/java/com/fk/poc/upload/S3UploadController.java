package com.fk.poc.upload;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class S3UploadController {

    @Autowired
    private S3FileService s3FileService;

    @PostMapping(value = "/files")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return s3FileService.upload(file.getOriginalFilename(), file.getBytes());
    }

    @GetMapping(value = "/files")
    public List<String> files() {
        return s3FileService.listFiles();
    }

}
