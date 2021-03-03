package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Certification extends ProfessionalSkill {

    private static final long serialVersionUID = 1L;

    private List<Experience> detail;

    public Certification() {
    }

    public List<Experience> getDetail() {
        return detail;
    }

    public Certification(List<Experience> detail) {
        Objects.requireNonNull(detail, "list most not be null");
        this.detail = detail;
    }

    public void addExperience(String name, String url, Experience.PeriodPosition position) {
        BaseInf inf = new BaseInf(name, url);
        boolean isExist = false;
        for (Experience ex :  detail) {
            if (inf.equals(ex.getHomePage())) {
                ex.addPeriodPosition(position);
                isExist = true;
            }
        }
        if (!isExist) {
            detail.add(new Experience(inf, position));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certification)) return false;

        Certification that = (Certification) o;

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
