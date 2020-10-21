package com.machula.services.impl;

import com.machula.entities.ImageEntity;
import com.machula.models.PictureModel;
import com.machula.models.PicturePreviewModel;
import com.machula.services.CacheService;
import com.machula.services.ImageService;
import com.machula.services.RestService;
import com.machula.services.ScheduledTasksService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledTasksServiceImpl implements ScheduledTasksService {

    private final CacheService cacheService;
    private final ImageService imageService;
    private final RestService restService;

    public ScheduledTasksServiceImpl(CacheService cacheService, ImageService imageService, RestService restService) {
        this.cacheService = cacheService;
        this.imageService = imageService;
        this.restService = restService;
    }

    @PostConstruct
    public void init() throws IOException {
        updateCache();
    }

    @Scheduled(cron = "${app.cron.param.update.pictures}")
    public void updateCache() throws IOException {

        cacheService.deleteFiles(imageService.findAll());

        String token = restService.getToken();

        List<PicturePreviewModel> picturePreviewModelList = restService.getPictureList(token);

        List<ImageEntity> imageEntityList = new ArrayList<>();

        for (PicturePreviewModel previewModel : picturePreviewModelList) {
            PictureModel pictureModel = restService.getPictureModelById(token, previewModel.getId());

            byte[] croppedPicture = restService.getPictureByUrl(token, pictureModel.getCroppedPicture());
            byte[] fullPicture = restService.getPictureByUrl(token, pictureModel.getFullPicture());

            String pathCroppedPicture = "src\\main\\resources\\pictures\\cropped\\" + pictureModel.getId() + ".jpg";
            String pathFullPicture = "src\\main\\resources\\pictures\\full\\" + pictureModel.getId() + ".jpg";

            cacheService.createFile(croppedPicture, pathCroppedPicture);
            cacheService.createFile(fullPicture, pathFullPicture);

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setId(pictureModel.getId());
            imageEntity.setAuthor(pictureModel.getAuthor());
            imageEntity.setCamera(pictureModel.getCamera());
            imageEntity.setTags(pictureModel.getTags());
            imageEntity.setCroppedPicture(pathCroppedPicture);
            imageEntity.setFullPicture(pathFullPicture);

            imageEntityList.add(imageEntity);
        }

        imageService.saveAll(imageEntityList);
    }
}
