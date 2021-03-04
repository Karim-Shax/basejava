package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<Organization> detail;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> detail) {
        Objects.requireNonNull(detail, "list most not be null");
        this.detail = detail;
    }

    public List<Organization> getDetail() {
        return detail;
    }

    public void addExperience(String name, String url, Organization.Period position) {
        Link inf = new Link(name, url);
        boolean isExist = false;
        for (Organization ex : detail) {
            if (inf.equals(ex.getHomePage())) {
                ex.addPeriodPosition(position);
                isExist = true;
            }
        }
        if (!isExist) {
            detail.add(new Organization(inf, position));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationSection)) return false;

        OrganizationSection that = (OrganizationSection) o;

        return detail.equals(that.detail);
    }

    @Override
    public int hashCode() {
        return detail.hashCode();
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "detail=" + detail.toString() +
                '}';
    }
}
