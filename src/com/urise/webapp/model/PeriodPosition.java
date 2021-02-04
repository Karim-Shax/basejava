package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodPosition {

    private final String title;
    private final LocalDate startTime;
    private final LocalDate endTime;
    private final String TechnologyNameVersion;

    public PeriodPosition(String title, LocalDate startTime, LocalDate endTime, String technologyNameVersion) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(endTime, "endTime must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        TechnologyNameVersion = technologyNameVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodPosition)) return false;

        PeriodPosition that = (PeriodPosition) o;

        if (!title.equals(that.title)) return false;
        if (!startTime.equals(that.startTime)) return false;
        if (!endTime.equals(that.endTime)) return false;
        return Objects.equals(TechnologyNameVersion, that.TechnologyNameVersion);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + (TechnologyNameVersion != null ? TechnologyNameVersion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PeriodPosition{" +
                "title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", TechnologyNameVersion='" + TechnologyNameVersion + '\'' +
                '}';
    }
}
