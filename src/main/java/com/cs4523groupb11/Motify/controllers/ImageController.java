package com.cs4523groupb11.Motify.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/image")
@PreAuthorize("hasRole('USER')")
public class ImageController {

    @GetMapping("/getByPath/{path}")
    public ResponseEntity<byte[]> getImageByPath(@PathVariable String path){
        byte[] imageBytes;

        try{
            imageBytes = Files.readAllBytes(Paths.get(path));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}
