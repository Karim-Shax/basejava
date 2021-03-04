package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization extends Section {

    private static final long serialVersionUID = 1L;
    private Link homePage;
    private  List<Period> list = new ArrayList<>();

    public Organization() {

    }

    public Organization(String name, String url, LocalDate startTime, LocalDate endTime, String title, String technologyNameVersion) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(endTime, "endTime must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        addPeriodPosition(new Period(title, startTime, endTime, technologyNameVersion));
    }

    public Organization(Link homePage, Period position) {
        this.homePage = homePage;
        addPeriodPosition(position);
    }

    public Organization(Link homePage) {
        this.homePage = homePage;
    }

    public void addPeriodPosition(Period position) {
        list.add(position);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getList() {
        return list;
    }

    public void setList(List<Period> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + list.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nOrganization" + "\n" +
                "homePage=\t" + homePage +
                "list=\n" + list;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        private static final long serialVersionUID = 1L;
        private  String title;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private  LocalDate startTime;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private  LocalDate endTime;
        private  String technoLogyNameVersion;

        public Period() {
        }

        public Period(String title, LocalDate startTime, LocalDate endTime, String technologyNameVersion) {
            Objects.requireNonNull(startTime, "startTime must not be null");
            Objects.requireNonNull(endTime, "endTime must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
            technoLogyNameVersion = technologyNameVersion;
        }

        public String getTitle() {
            return title;
        }

        public LocalDate getStartTime() {
            return startTime;
        }

        public LocalDate getEndTime() {
            return endTime;
        }

        public String getTechnoLogyNameVersion() {
            return technoLogyNameVersion;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Period)) return false;

            Period that = (Period) o;

            if (!title.equals(that.title)) return false;
            if (!startTime.equals(that.startTime)) return false;
            if (!endTime.equals(that.endTime)) return false;
            return Objects.equals(technoLogyNameVersion, that.technoLogyNameVersion);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + startTime.hashCode();
            result = 31 * result + endTime.hashCode();
            result = 31 * result + (technoLogyNameVersion != null ? technoLogyNameVersion.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Period" + "\n" +
                    "title=\t\t" + title + "\n" +
                    "startTime=\t" + startTime + "\n" +
                    "endTime=\t" + endTime + "\n" +
                    "technoLogyNameVersion=" + technoLogyNameVersion + "\n";
        }
    }
}
