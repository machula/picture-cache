package com.machula.models;

import java.util.List;

public class PicturesResponse {
    private List<PicturePreviewModel> pictures;
    private int page;
    private int pageCount;
    private boolean hasMore;

    public PicturesResponse() {
    }

    public PicturesResponse(List<PicturePreviewModel> pictures, int page, int pageCount, boolean hasMore) {
        this.pictures = pictures;
        this.page = page;
        this.pageCount = pageCount;
        this.hasMore = hasMore;
    }

    public List<PicturePreviewModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturePreviewModel> pictures) {
        this.pictures = pictures;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
