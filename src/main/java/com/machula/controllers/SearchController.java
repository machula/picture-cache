package com.machula.controllers;

import com.machula.models.dto.ImageDto;
import com.machula.services.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ImageService imageService;

    public SearchController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{searchTerm}")
    public List<ImageDto> searchImage(@PathVariable String searchTerm) throws IOException {
        return imageService.findAllBySearchTerm(searchTerm)
                .stream()
                .map(imageService::convertToImageDto)
                .collect(Collectors.toList());
    }

}
