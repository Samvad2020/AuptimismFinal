package com.example.journal.model;

public class DailyLifeMediaItem {
    String url;
    boolean video;
    boolean image;

    public DailyLifeMediaItem(String url, boolean video, boolean image) {
        this.url = url;
        this.video = video;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
