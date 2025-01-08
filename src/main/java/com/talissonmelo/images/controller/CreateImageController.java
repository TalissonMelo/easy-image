package com.talissonmelo.images.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreateImageController {

    @PostMapping("/v1/images")
    public ResponseEntity execute(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name,
                                  @RequestParam("tags") List<String> tags) {


        System.err.println(file.getOriginalFilename());
        System.err.println(file.getSize());
        System.err.println(name);
        System.err.println(tags);

        return ResponseEntity.ok().build();
    }
}
