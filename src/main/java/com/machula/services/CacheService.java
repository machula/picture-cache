package com.machula.services;

import com.machula.entities.ImageEntity;

import java.io.IOException;
import java.util.List;

public interface CacheService {
    void createFile(byte[] bytes, String fileName) throws IOException;
    void deleteFiles(List<ImageEntity> paths) throws IOException;
}
