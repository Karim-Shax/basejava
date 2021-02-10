package com.urise.webapp.model;

import java.util.Objects;
import java.util.StringJoiner;

public class BaseInf implements ProfessionalSkill {

    private final String title;
    private final String urlText;

    public BaseInf(String title, String text) {
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.urlText = text;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlText() {
        return urlText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseInf)) return false;

        BaseInf baseInf = (BaseInf) o;

        if (!title.equals(baseInf.title)) return false;
        return Objects.equals(urlText, baseInf.urlText);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (urlText != null ? urlText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\bBaseInf" + "\n" +
                "title=\t\t" + title + "\n" +
                "urlText=\t" + urlText + "\n";
    }
}
