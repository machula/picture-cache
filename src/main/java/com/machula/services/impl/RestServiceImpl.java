package com.machula.services.impl;

import com.machula.models.*;
import com.machula.services.RestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestServiceImpl implements RestService {
    private RestTemplate restTemplate;

    @Value("${app.rest.api.host}")
    private String apiHost;

    @Value("${app.rest.api.key}")
    private String apiKey;

    @Value("${app.rest.api.path.auth}")
    private String authPath;

    @Value("${app.rest.api.path.images}")
    private String imagesPath;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
    }

    @Override
    public List<PicturePreviewModel> getPictureList(String token) {
        String url = apiHost + imagesPath;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<PicturesResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, PicturesResponse.class);

        PicturesResponse picturesResponse = responseEntity.getBody();

        List<PicturePreviewModel> picturePreviewModelList = new ArrayList<>();

        picturePreviewModelList.addAll(picturesResponse.getPictures());

        boolean hasMore = picturesResponse.isHasMore();

        int page = picturesResponse.getPage();
        int totalElements = picturesResponse.getPageCount() * 10;

        while (hasMore){
            PicturesResponse response = getPicturePage(token, ++page, totalElements);
            picturePreviewModelList.addAll(response.getPictures());
            hasMore = response.isHasMore();
        }


        return picturePreviewModelList;
    }

    @Override
    public PicturesResponse getPicturePage(String token, int page, int limit) {
        String url = UriComponentsBuilder.fromHttpUrl(apiHost + imagesPath)
                .queryParam("page", page)
                .queryParam("limit", limit)
                .build()
                .toUriString();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, PicturesResponse.class).getBody();
    }

    @Override
    public byte[] getPictureByUrl(String token, String url) {
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        return responseEntity.getBody();
    }

    @Override
    public PictureModel getPictureModelById(String token, String id) {
        String url = apiHost + imagesPath + "/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<PictureModel> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, PictureModel.class);

        return responseEntity.getBody();
    }


    @Override
    public String getToken(){
        String url = apiHost + authPath;
        AuthModelRequest authModelRequest = new AuthModelRequest(apiKey);
        ResponseEntity<AuthModelResponse> responseEntity = restTemplate.postForEntity(url, authModelRequest, AuthModelResponse.class);
        AuthModelResponse authModel = responseEntity.getBody();

        return authModel.getToken();
    }
}
