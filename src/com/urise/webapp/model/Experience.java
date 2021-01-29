package com.urise.webapp.model;

import java.time.LocalDate;

public class Experience {

    private String Company;
    private LocalDate startTime;
    private LocalDate endTime;
    private String TechnologyNameVersion;


    public Experience(String company, LocalDate startTime, LocalDate endTime, String technologyNameVersion) {
        Company = company;
        this.startTime = startTime;
        this.endTime = endTime;
        TechnologyNameVersion = technologyNameVersion;
    }

    public Experience() {
    }

    public void setCompany(String company) {
        Company = company;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getTechnologyNameVersion() {
        return TechnologyNameVersion;
    }

    public void setTechnologyNameVersion(String technologyNameVersion) {
        TechnologyNameVersion = technologyNameVersion;
    }

    public String getText() {
        return Company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;

        Experience that = (Experience) o;

        if (Company != null ? !Company.equals(that.Company) : that.Company != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        return TechnologyNameVersion != null ? TechnologyNameVersion.equals(that.TechnologyNameVersion) : that.TechnologyNameVersion == null;
    }

    @Override
    public int hashCode() {
        int result = Company != null ? Company.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (TechnologyNameVersion != null ? TechnologyNameVersion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n" + "Experience{" + "\n" +
                "Company='" + Company + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime + "\n" +
                "TechnologyNameVersion=" + "\n" + TechnologyNameVersion +
                " }" + "\n";
    }
}
