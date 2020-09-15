package com.project.khopt.fitassistant;

public class SportElement {

    private String url;
    private String name;

    public  SportElement(){

    }

    public SportElement(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
