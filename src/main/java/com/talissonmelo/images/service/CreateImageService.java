package com.talissonmelo.images.service;


import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.domain.enums.ImageExtension;
import com.talissonmelo.images.repository.ImageRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service
@Transactional
public class CreateImageService {


    private ImageRepository imageRepository;

    public CreateImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public URI createImage(MultipartFile file, String name, List<String> tags) throws IOException {
        Image image = Image.to(name, file.getSize(), ImageExtension.valueOf(MediaType.valueOf(file.getContentType())), tags, file.getBytes());
        imageRepository.save(image);
        return buildImageURL(image);
    }


    private URI buildImageURL(Image image){
        String imagePath = "/" + image.getImageId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(imagePath)
                .build().toUri();
    }
}
