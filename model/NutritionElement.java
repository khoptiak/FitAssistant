package com.project.khopt.fitassistant.model;

public class NutritionElement {
    private String title;
    private String iconUrl;

    public NutritionElement(){}
    public NutritionElement(String title, String iconUrl) {
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
