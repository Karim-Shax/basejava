package com.urise.webapp.model;

public enum ContactType {
    PHONE("Phone"),
    SKYPE("Skype"){
    },
    EMAIL("Email"){
    },
    LINKEDIN("LinkedIn"){
    },
    GITHUB("GitHub"){
    },
    STACKOVERFLOW("StackOverFlow"){
    },
    HOME_PAGE("Home Page") {
    };
    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
