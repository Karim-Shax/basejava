package com.urise.webapp.model;

import java.util.Map;

public class TechExperience implements ProfessionalSkill{
    public  String title;
    public  Map<String,ExperienceEducationInf> experienceInfMap;

    public TechExperience(String title, Map<String, ExperienceEducationInf> experienceInfMap) {
        this.title = title;
        this.experienceInfMap = experienceInfMap;
    }

    public TechExperience() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, ExperienceEducationInf> getExperienceInfMap() {
        return experienceInfMap;
    }

    public void setExperienceInfMap(Map<String, ExperienceEducationInf> experienceInfMap) {
        this.experienceInfMap = experienceInfMap;
    }

    @Override
    public String getText() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechExperience)) return false;

        TechExperience that = (TechExperience) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return experienceInfMap != null ? experienceInfMap.equals(that.experienceInfMap) : that.experienceInfMap == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (experienceInfMap != null ? experienceInfMap.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechExperience{" +
                "title='" + title + '\'' +
                ", experienceInfMap=" + experienceInfMap.toString() +
                '}';
    }
}
