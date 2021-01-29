package com.urise.webapp.model;

public enum ContentType {
    PHONE("Phone"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackoverFlow");
    private String title;

    ContentType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
