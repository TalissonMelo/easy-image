package com.talissonmelo.images.controller;


import com.talissonmelo.images.service.CreateImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class CreateImageController {

    private CreateImageService createImageService;

    public CreateImageController(CreateImageService createImageService) {
        this.createImageService = createImageService;
    }

    @PostMapping("/v1/images")
    public ResponseEntity execute(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name,
                                  @RequestParam("tags") List<String> tags) throws IOException {


        URI image = createImageService.createImage(file, name, tags);


        return ResponseEntity.created(image).build();
    }
}
