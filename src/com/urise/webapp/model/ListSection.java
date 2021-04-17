package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<String> items;

    public ListSection() {

    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    @Override
    public String toHtml() {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>\n" +
                "            <td colspan=\"2\">\n" +
                "                <ul>\n");
        String form = "<li>%s</li>\n";
        for (String s : items) {
            builder.append(String.format(form, s));
        }
        builder.append(" </ul>\n" +
                "            </td>\n" +
                "        </tr>");
        return builder.toString();
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}

