package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Certification<T> implements ProfessionalSkill {
    private final List<T> detail;

    public Certification(List<T> detail) {
        Objects.requireNonNull(detail, "list most not be null");
        this.detail = detail;
    }

    public List<T> getDetail() {
        return detail;
    }

    public void addExperience(String name, String url, PeriodPosition position) {
        BaseInf inf = new BaseInf(name, url);
        boolean isExist = false;
        for (Experience ex : (List<Experience>) detail) {
            if (inf.equals(ex.getHomePage())) {
                ex.addPeriodPosition(position);
                isExist = true;
            }
        }
        if (!isExist) {
            detail.add((T) new Experience(inf, position));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certification)) return false;

        Certification<?> that = (Certification<?>) o;

        return detail.equals(that.detail);
    }

    @Override
    public int hashCode() {
        return detail.hashCode();
    }

    @Override
    public String toString() {
        return "Certification{" +
                "detail=" + detail +
                '}';
    }
}
