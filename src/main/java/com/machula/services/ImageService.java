package com.machula.services;

import com.machula.entities.ImageEntity;
import com.machula.models.dto.ImageDto;

import java.util.List;

public interface ImageService {
    List<ImageEntity> findAll();
    void saveAll(List<ImageEntity> imageEntityList);
    List<ImageEntity> findAllBySearchTerm(String searchTerm);
    ImageDto convertToImageDto(ImageEntity imageEntity);


}
