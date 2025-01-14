package com.talissonmelo.images.controller.response;

import com.talissonmelo.images.domain.Image;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

public record ImageDTO(String url, String name, String extension, Long size, LocalDate uploadDate) {

    public static ImageDTO toImageDto(Image image) {
        String url = String.valueOf(buildImageURL(image));
        return new ImageDTO(url, image.getExtension().name(), image.getName(), image.getSize(), image.getUploadDate().toLocalDate());
    }

    private static URI buildImageURL(Image image) {
        String imagePath = "/" + image.getImageId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(imagePath)
                .build().toUri();
    }
}
