package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private final String uuid;
    private final String fullName;
    private Map<ContactType, BaseInf> contacts;
    private Map<PersonInf, ProfessionalSkill> info;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setInfo(EnumMap<PersonInf, ProfessionalSkill> info) {
        this.info = info;
    }

    public Map<ContactType, BaseInf> getContacts() {
        return contacts;
    }

    public void setContacts(EnumMap<ContactType, BaseInf> contacts) {
        this.contacts = contacts;
    }

    public Map<PersonInf, ProfessionalSkill> getInfo() {
        return info;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public BaseInf getCont(ContactType type) {
        return contacts.get(type);
    }

    public ProfessionalSkill getProfSkill(PersonInf inf) {
        return info.get(inf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        if (!Objects.equals(uuid, resume.uuid)) return false;
        if (!Objects.equals(fullName, resume.fullName)) return false;
        if (!Objects.equals(contacts, resume.contacts)) return false;
        return Objects.equals(info, resume.info);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", info=" + info +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
