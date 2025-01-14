package com.talissonmelo.images.service;


import com.talissonmelo.images.controller.response.ImageDTO;
import com.talissonmelo.images.domain.Image;
import com.talissonmelo.images.domain.enums.ImageExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetImageSearchService {

    private MongoTemplate mongoTemplate;

    public GetImageSearchService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<ImageDTO> search(ImageExtension extension, String query) {
        Query mongoQuery = new Query();

        List<Criteria> criteriaList = new ArrayList<>();

        if (extension != null) {
            criteriaList.add(Criteria.where("extension").is(extension));
        }

        if (query != null && !query.isEmpty()) {
            criteriaList.add(new Criteria().orOperator(
                    Criteria.where("name").regex(query, "i"),
                    Criteria.where("tags").regex(query, "i")
            ));
        }

        if (!criteriaList.isEmpty()) {
            mongoQuery.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        List<Image> result = mongoTemplate.find(mongoQuery, Image.class);

        return result.stream().map(image -> ImageDTO.toImageDto(image)).collect(Collectors.toList());
    }
}
