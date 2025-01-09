package com.talissonmelo.images.domain;


import com.talissonmelo.images.domain.enums.ImageExtension;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tb_image")
@Data
public class Image {

    @MongoId
    private String imageId;

    private String name;

    private Long size;

    private ImageExtension extension;

    private LocalDateTime uploadDate = LocalDateTime.now();

    private List<String> tags;

    private byte[] file;

}
