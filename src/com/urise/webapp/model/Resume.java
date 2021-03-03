package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private Map<ContactType, String> contacts;
    private Map<PersonInf, ProfessionalSkill> info;

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        contacts = new EnumMap<ContactType, String>(ContactType.class);
    }

    public Map<PersonInf, ProfessionalSkill> getInfo() {
        return info;
    }

    public void setInfo(EnumMap<PersonInf, ProfessionalSkill> info) {
        this.info = info;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContacts(EnumMap<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCont(ContactType type) {
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
        return "Resume" + "\n" +
                "uuid=\t\t" + uuid + "\n" +
                "fullName=\t'" + fullName + "\n" +
                "contacts=\t" + contacts + "\n" +
                "info=" + info +
                "\n";
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    public void addContact(ContactType valueOf, String readUTF) {
        contacts.put(valueOf, readUTF);
    }
}
