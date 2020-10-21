package com.machula.models.dto;

public class ImageDto {
    private String id;
    private String author;
    private String tags;
    private String camera;

    public ImageDto() {
    }

    public ImageDto(String id, String author, String tags, String camera) {
        this.id = id;
        this.author = author;
        this.tags = tags;
        this.camera = camera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }
}
