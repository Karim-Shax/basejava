package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private final String uuid;
    private String fullName;

    private EnumMap <Contacts,BaseInf> contacts;
    private EnumMap<PersonInf,ProfessionalSkill> info;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public EnumMap<Contacts, BaseInf> getContacts() {
        return contacts;
    }

    public void setContacts(EnumMap<Contacts, BaseInf> contacts) {
        this.contacts = contacts;
    }

    public EnumMap<PersonInf, ProfessionalSkill> getInfo() {
        return info;
    }

    public void setInfo(EnumMap<PersonInf, ProfessionalSkill> info) {
        this.info = info;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        if (!Objects.equals(uuid, resume.uuid)) return false;
        return Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
