package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseInf implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String urlText;

    public BaseInf(String title, String text) {
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.urlText = text;
    }

    public BaseInf() {
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
        return "BaseInf{" +
                "title='" + title +
                ", urlText='" + urlText +
                "}\n";
    }
}
