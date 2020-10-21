package com.machula.services;

import com.machula.models.PictureModel;
import com.machula.models.PicturePreviewModel;
import com.machula.models.PicturesResponse;

import java.util.List;

public interface RestService {
    String getToken();
    List<PicturePreviewModel> getPictureList(String token);
    PicturesResponse getPicturePage(String token, int page, int limit);
    byte[] getPictureByUrl(String token, String url);
    PictureModel getPictureModelById(String token, String id);
}
