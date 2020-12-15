package com.project.khopt.fitassistant.model;

public class ExerciseElement {

    private String title;
    private String videoUrl;

    public  ExerciseElement(){
    }

    public ExerciseElement(String url, String title) {
        this.videoUrl = url;
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getTitle() {
        return title;
    }
}
