package com.talissonmelo.images.controller;


import com.talissonmelo.images.controller.response.ImageDTO;
import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.domain.enums.ImageExtension;
import com.talissonmelo.images.service.GetImageSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GetImageSearchController {

    private GetImageSearchService getImageSearchService;

    public GetImageSearchController(GetImageSearchService getImageSearchService) {
        this.getImageSearchService = getImageSearchService;
    }


    @GetMapping("/v1/images")
    public ResponseEntity<List<ImageDTO>> search(
            @RequestParam(value = "extension", required = false, defaultValue = "") ImageExtension extension,
            @RequestParam(value = "query", required = false) String query) throws InterruptedException {

        List<ImageDTO> result = getImageSearchService.search(extension, query);

        return ResponseEntity.ok(result);
    }


}
