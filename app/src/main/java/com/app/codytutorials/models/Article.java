package com.app.codytutorials.models;


public class Article {
    public String title;
    private String audioUrl;

    public Article() {
    }

    public Article(String title, String audioUrl) {
        this.title = title;
        this.audioUrl = audioUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
