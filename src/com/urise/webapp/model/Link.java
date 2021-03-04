package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String url;

    public Link(String title, String text) {
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.url = text;
    }

    public Link() {
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        if (!title.equals(link.title)) return false;
        return Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "title='" + title +
                ", url='" + url +
                "}\n";
    }
}
