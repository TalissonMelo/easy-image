package com.talissonmelo.images.controller;


import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.service.CreateImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CreateImageController {
    @Autowired
    private CreateImageService createImageService;

    @PostMapping("/v1/images")
    public ResponseEntity execute(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name,
                                  @RequestParam("tags") List<String> tags) throws IOException {


        Image image = createImageService.createImage(file, name, tags);


        return ResponseEntity.status(201).body(image);
    }
}
