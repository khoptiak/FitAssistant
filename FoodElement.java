package com.project.khopt.fitassistant;

public class FoodElement {

    private String title;
    private String iconUrl;

    public FoodElement(String title, String iconUrl) {
        this.title = title;
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
