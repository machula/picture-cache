package com.machula.services.impl;

import com.machula.entities.ImageEntity;
import com.machula.models.dto.ImageDto;
import com.machula.repositories.ImageRepository;
import com.machula.services.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageEntity> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public void saveAll(List<ImageEntity> imageEntityList) {
        imageRepository.saveAll(imageEntityList);
    }

    @Override
    public List<ImageEntity> findAllBySearchTerm(String searchTerm) {
        return imageRepository.findAllBySearchTerm(searchTerm);
    }

    @Override
    public ImageDto convertToImageDto(ImageEntity imageEntity) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(imageEntity.getId());
        imageDto.setAuthor(imageEntity.getAuthor());
        imageDto.setTags(imageEntity.getTags());
        imageDto.setCamera(imageEntity.getCamera());

        return imageDto;
    }
}
