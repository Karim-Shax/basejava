package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ObjectDataImpl implements IOStrategy {
    @Override
    public void writeObj(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> info = r.getInfo();
            dos.writeInt(info.size());
            info.forEach((key, skill) -> {
                try {
                    dos.writeUTF(key.name());
                    writeModelField(skill, dos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void writeModelField(Section skill, DataOutputStream dos) throws IOException {
        String[] classs = skill.getClass().getName().split("\\.");
        dos.writeUTF(classs[classs.length - 1]);
        switch (classs[classs.length - 1]) {
            case "TextSection":
                TextSection section = (TextSection) skill;
                dos.writeUTF(section.getText());
                break;
            case "ListSection":
                ListSection section1 = (ListSection) skill;
                dos.writeInt(section1.getItems().size());
                for (String str : section1.getItems()) {
                    dos.writeUTF(str);
                }
                break;
            case "OrganizationSection":
                OrganizationSection organizationSection = (OrganizationSection) skill;
                dos.writeInt(organizationSection.getDetail().size());
                for (Organization ex : organizationSection.getDetail()) {
                    writeModelField(ex, dos);
                }
                break;
            case "Organization":
                Organization organization = (Organization) skill;
                dos.writeUTF(organization.getHomePage().getTitle());
                if (organization.getHomePage().getUrl() == null) {
                    dos.writeUTF("null");
                } else {
                    dos.writeUTF(organization.getHomePage().getUrl());
                }
                dos.writeInt(organization.getList().size());
                for (Organization.Period per : organization.getList()) {
                    dos.writeUTF(per.getTitle());
                    dos.writeInt(per.getStartTime().getYear());
                    dos.writeInt(per.getStartTime().getMonth().getValue());
                    dos.writeInt(per.getStartTime().getDayOfMonth());
                    dos.writeInt(per.getEndTime().getYear());
                    dos.writeInt(per.getEndTime().getMonth().getValue());
                    dos.writeInt(per.getEndTime().getDayOfMonth());
                    if (per.getTechnoLogyNameVersion() == null) {
                        dos.writeUTF("null");
                    } else {
                        dos.writeUTF(per.getTechnoLogyNameVersion());
                    }
                }
                break;
        }
    }

    @Override
    public Resume readObj(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int mapSize = dis.readInt();
            resume.setInfo(readModelMap(dis, mapSize));
            return resume;
        }
    }


    private EnumMap<SectionType, Section> readModelMap(DataInputStream is, int size) throws IOException {
        EnumMap<SectionType, Section> map = new EnumMap<>(SectionType.class);
        for (int i = 0; i < size; i++) {
            map.put(SectionType.valueOf(is.readUTF()), readSkill(is));
        }
        return map;
    }

    private Section readSkill(DataInputStream is) throws IOException {
        String skillName = is.readUTF();
        Section skill = null;
        switch (skillName) {
            case "TextSection":
                skill = new TextSection(is.readUTF());
                break;
            case "ListSection":
                int size = is.readInt();
                List<String> text = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    text.add(is.readUTF());
                }
                skill = new ListSection(text);
                break;
            case "OrganizationSection":
                int size1 = is.readInt();
                List<Organization> organizations = new ArrayList<>();
                for (int i = 0; i < size1; i++) {
                    organizations.add((Organization) readSkill(is));
                }
                skill = new OrganizationSection(organizations);
                break;
            case "Organization":
                String title = is.readUTF();
                String urlBaseInf = is.readUTF();
                if (urlBaseInf.equals("null")) {
                    urlBaseInf = null;
                }
                Link inf = new Link(title, urlBaseInf);
                int size2 = is.readInt();
                Organization organization = new Organization(inf);
                for (int i = 0; i < size2; i++) {
                    String url = is.readUTF();
                    LocalDate startDate = LocalDate.of(is.readInt(), is.readInt(), is.readInt());
                    LocalDate endDate = LocalDate.of(is.readInt(), is.readInt(), is.readInt());
                    String techVersion = is.readUTF();
                    if (techVersion.equals("null")) {
                        techVersion = null;
                    }
                    organization.addPeriodPosition(new Organization.Period(
                            url,
                            startDate,
                            endDate,
                            techVersion
                    ));
                }
                skill = organization;
                break;
        }
        return skill;
    }

}
