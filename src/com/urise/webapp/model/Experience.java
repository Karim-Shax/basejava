package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Experience implements ProfessionalSkill {

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
        return "Experience{" +
                "homePage=" + homePage +
                ", list=" + list +
                '}';
    }
}
