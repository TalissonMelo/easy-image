package com.talissonmelo.images.service;


import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.domain.enums.ImageExtension;
import com.talissonmelo.images.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image createImage(MultipartFile file, String name, List<String> tags) throws IOException {
        System.err.println(file);
        Image image = Image.to(name, file.getSize(), ImageExtension.valueOf(MediaType.valueOf(file.getContentType())), tags, file.getBytes());
        return imageRepository.save(image);
    }
}
