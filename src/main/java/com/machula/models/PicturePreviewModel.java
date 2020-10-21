package com.machula.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PicturePreviewModel {
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedPicture;

    public PicturePreviewModel() {
    }

    public PicturePreviewModel(String id, String croppedPicture) {
        this.id = id;
        this.croppedPicture = croppedPicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCroppedPicture() {
        return croppedPicture;
    }

    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }
}
