package com.machula.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureModel extends PicturePreviewModel{
    private String author;
    private String camera;
    private String tags;
    @JsonProperty("full_picture")
    private String fullPicture;

    public PictureModel() {
        super();
    }

    public PictureModel(String author, String camera, String tags, String fullPicture) {
        this.author = author;
        this.camera = camera;
        this.tags = tags;
        this.fullPicture = fullPicture;
    }

    public PictureModel(String id, String croppedPicture, String author, String camera, String tags, String fullPicture) {
        super(id, croppedPicture);
        this.author = author;
        this.camera = camera;
        this.tags = tags;
        this.fullPicture = fullPicture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFullPicture() {
        return fullPicture;
    }

    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }
}
