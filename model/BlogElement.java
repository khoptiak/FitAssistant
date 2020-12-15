package com.project.khopt.fitassistant.model;

public class BlogElement {

    private String iconUrl;
    private String title;
    private String content;



    public BlogElement(){}

    public BlogElement(String iconUrl, String title, String content) {
        this.iconUrl = iconUrl;
        this.title = title;
        this.content = content;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
