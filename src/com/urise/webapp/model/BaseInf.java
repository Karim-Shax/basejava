package com.urise.webapp.model;

public class BaseInf implements ProfessionalSkill {
    private String title;
    private String text;

    public BaseInf() {
    }

    public BaseInf(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseInf)) return false;

        BaseInf baseInf = (BaseInf) o;

        if (title != null ? !title.equals(baseInf.title) : baseInf.title != null) return false;
        return text != null ? text.equals(baseInf.text) : baseInf.text == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseInf{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
