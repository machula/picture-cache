package com.machula.services.impl;

import com.machula.entities.ImageEntity;
import com.machula.services.CacheService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    @Override
    public void createFile(byte[] bytes, String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
    }

    @Override
    public void deleteFiles(List<ImageEntity> imageEntityList) throws IOException {
        for (ImageEntity imageEntity : imageEntityList) {
            Files.delete(Paths.get(imageEntity.getCroppedPicture()));
            Files.delete(Paths.get(imageEntity.getFullPicture()));
        }
    }


}
