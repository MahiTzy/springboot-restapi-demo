package com.springboot.rest.springrestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.rest.springrestapi.helper.FileUploadHelper;

@RestController
public class FileUpload {
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("profile") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        try {
            if (file.isEmpty()) {
                return ResponseEntity.ok("File is empty");
            }
            if (file.getSize() < 1048576) {
                return ResponseEntity.ok("File size is less than 1MB");
            }
            if (!file.getContentType().equals("image/jpeg")) {
                return ResponseEntity.ok("File type is not supported");
            }
            if (fileUploadHelper.uploadFile(file)) {
                // return ResponseEntity.ok("File uploaded successfully");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("File not uploaded!");
    }
}
