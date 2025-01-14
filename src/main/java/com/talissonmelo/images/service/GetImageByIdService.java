package com.talissonmelo.images.service;


import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class GetImageByIdService {

    private ImageRepository imageRepository;

    public GetImageByIdService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getById(String imageId) {

        return imageRepository.findById(imageId).orElse(null);

    }
}
