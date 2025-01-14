package com.talissonmelo.images.controller;


import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.domain.enums.ImageExtension;
import com.talissonmelo.images.service.GetImageByIdService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetImageByIdController {

    private GetImageByIdService getImageByIdService;

    public GetImageByIdController(GetImageByIdService getImageByIdService) {
        this.getImageByIdService = getImageByIdService;
    }

    @GetMapping("/v1/images/{imageId}")
    public ResponseEntity<byte[]> execute(@PathVariable String imageId) {

        Image image = getImageByIdService.getById(imageId);

        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(ImageExtension.valueOf(image.getExtension()));
            headers.setContentLength(image.getSize());
            headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() +  "\"", image.getFileName());

            return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}
