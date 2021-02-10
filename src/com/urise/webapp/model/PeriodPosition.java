package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodPosition {

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
