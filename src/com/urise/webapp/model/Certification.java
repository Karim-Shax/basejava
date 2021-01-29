package com.urise.webapp.model;

import java.util.List;

public class Certification<T> implements ProfessionalSkill {
    private String title;
    private List<T> detail;

    public Certification() {
    }

    public Certification(String title, List<T> detail) {
        this.title = title;
        this.detail = detail;
    }

    public void setDetail(List<T> detail) {
        this.detail = detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getDetail() {
        return detail;
    }

    @Override
    public String getText() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certification)) return false;

        Certification that = (Certification) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return detail != null ? detail.equals(that.detail) : that.detail == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "title=" + title + "\n" +
                ", detail=" + detail.toString() + '}' + "\n";
    }
}
