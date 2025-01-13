package com.talissonmelo.images.domain;


import com.talissonmelo.images.domain.enums.ImageExtension;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "tb_image")
public class Image {

    @MongoId
    private String imageId;

    private String name;

    private Long size;

    private ImageExtension extension;

    private LocalDateTime uploadDate = LocalDateTime.now();

    private List<String> tags;

    private byte[] file;

    private Image(String imageId, String name, Long size, ImageExtension extension, List<String> tags, byte[] file) {
        this.imageId = imageId;
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.tags = tags;
        this.file = file;
    }

    public static Image to(String name, Long size, ImageExtension extension, List<String> tags, byte[] file) {
        return new Image(UUID.randomUUID().toString(), name, size, extension, tags, file);
    }

    public String getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public ImageExtension getExtension() {
        return extension;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public byte[] getFile() {
        return file;
    }

    public String getFileName(){
        return getName().concat(".").concat(getExtension().name());
    }
}
