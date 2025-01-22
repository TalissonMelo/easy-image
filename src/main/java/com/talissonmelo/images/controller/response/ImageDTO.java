package com.talissonmelo.images.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talissonmelo.images.domain.Image;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

public record ImageDTO(String url, String name, String extension, Long size, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate uploadDate) {

    public static ImageDTO toImageDto(Image image) {
        String url = String.valueOf(buildImageURL(image));
        return new ImageDTO(url, image.getName(), image.getExtension().name(), image.getSize(), image.getUploadDate().toLocalDate());
    }

    private static URI buildImageURL(Image image) {
        String imagePath = "/" + image.getImageId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(imagePath)
                .build().toUri();
    }
}
