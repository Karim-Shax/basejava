package com.urise.webapp.model;

public enum PersonInf {

    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    PersonInf(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}