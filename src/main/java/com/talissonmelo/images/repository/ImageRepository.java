package com.talissonmelo.images.repository;

import com.talissonmelo.images.domain.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {

}
