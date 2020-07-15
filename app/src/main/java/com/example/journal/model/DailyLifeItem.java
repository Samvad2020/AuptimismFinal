package com.example.journal.model;

public class DailyLifeItem {
    private String name;
    private String timeLeft;
    private String timeSpent;
    private String difficulty;
    private int progress;
    private int id;
    private String imgActivityUrl;

    public DailyLifeItem(String name, String timeLeft, String timeSpent, String difficulty, int progress, int id, String imgActivityUrl) {
        this.name = name;
        this.timeLeft = timeLeft;
        this.timeSpent = timeSpent;
        this.difficulty = difficulty;
        this.progress = progress;
        this.id = id;
        this.imgActivityUrl=imgActivityUrl;
    }

    public String getImgActivityUrl() {
        return imgActivityUrl;
    }

    public void setImgActivityUrl(String imgActivityUrl) {
        this.imgActivityUrl = imgActivityUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
