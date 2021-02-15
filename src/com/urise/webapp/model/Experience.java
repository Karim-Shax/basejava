package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Experience implements ProfessionalSkill {
    private static final long serialVersionUID = 1L;
    private final BaseInf homePage;
    private final List<PeriodPosition> list = new ArrayList<>();

    public Experience(String name, String url, LocalDate startTime, LocalDate endTime, String title, String technologyNameVersion) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(endTime, "endTime must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new BaseInf(name, url);
        addPeriodPosition(new PeriodPosition(title, startTime, endTime, technologyNameVersion));
    }

    public Experience(BaseInf homePage, PeriodPosition position) {
        this.homePage = homePage;
        addPeriodPosition(position);
    }

    public void addPeriodPosition(PeriodPosition position) {
        list.add(position);
    }

    public BaseInf getHomePage() {
        return homePage;
    }

    public List<PeriodPosition> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;

        Experience that = (Experience) o;

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
        return "\nExperience" + "\n" +
                "homePage=\t" + homePage +
                "list=\n" + list;
    }

    public static class PeriodPosition implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String title;
        private final LocalDate startTime;
        private final LocalDate endTime;
        private final String technoLogyNameVersion;

        public PeriodPosition(String title, LocalDate startTime, LocalDate endTime, String technologyNameVersion) {
            Objects.requireNonNull(startTime, "startTime must not be null");
            Objects.requireNonNull(endTime, "endTime must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
            technoLogyNameVersion = technologyNameVersion;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PeriodPosition)) return false;

            PeriodPosition that = (PeriodPosition) o;

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
            return "PeriodPosition" + "\n" +
                    "title=\t\t" + title + "\n" +
                    "startTime=\t" + startTime + "\n" +
                    "endTime=\t" + endTime + "\n" +
                    "technoLogyNameVersion=" + technoLogyNameVersion + "\n";
        }
    }
}
