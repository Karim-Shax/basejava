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
            forEachWithExMap(contacts, (k, v) -> {
                dos.writeUTF(k.name());
                dos.writeUTF(v);
            });
            Map<SectionType, Section> info = r.getInfo();
            dos.writeInt(info.size());
            forEachWithExMap(info, (k, v) -> {
                dos.writeUTF(k.name());
                writeSection(v, dos);
            });
        }
    }

    private void writeSection(Section sectionArg, DataOutputStream dos) throws IOException {
        String[] classs = sectionArg.getClass().getName().split("\\.");
        dos.writeUTF(classs[classs.length - 1]);
        switch (classs[classs.length - 1]) {
            case "TextSection":
                TextSection section = (TextSection) sectionArg;
                dos.writeUTF(section.getText());
                break;
            case "ListSection":
                ListSection section1 = (ListSection) sectionArg;
                dos.writeInt(section1.getItems().size());
                forEachWithExList(section1.getItems(), dos::writeUTF);
                break;
            case "OrganizationSection":
                OrganizationSection organizationSection = (OrganizationSection) sectionArg;
                dos.writeInt(organizationSection.getDetail().size());
                forEachWithExList(organizationSection.getDetail(),(s)-> writeSection(s, dos));
                break;
            case "Organization":
                Organization organization = (Organization) sectionArg;
                dos.writeUTF(organization.getHomePage().getTitle());
                String url = organization.getHomePage().getUrl();
                String text = url == null ? "null" : url;
                dos.writeUTF(text);
                dos.writeInt(organization.getList().size());
                forEachWithExList(organization.getList(),(per)->{
                    dos.writeUTF(per.getTitle());
                    writeDate(dos, per.getStartTime());
                    writeDate(dos, per.getEndTime());
                    String techNVersion = per.getTechnoLogyNameVersion();
                    String value = techNVersion == null ? "null" : techNVersion;
                    dos.writeUTF(value);
                });
                break;
        }
    }

    public void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
        dos.writeInt(date.getDayOfMonth());
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
            resume.setInfo(readSections(dis, mapSize));
            return resume;
        }
    }


    private EnumMap<SectionType, Section> readSections(DataInputStream is, int size) throws IOException {
        EnumMap<SectionType, Section> map = new EnumMap<>(SectionType.class);
        for (int i = 0; i < size; i++) {
            map.put(SectionType.valueOf(is.readUTF()), readSection(is));
        }
        return map;
    }

    private Section readSection(DataInputStream is) throws IOException {
        String section = is.readUTF();
        Section skill = null;
        switch (section) {
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
                    organizations.add((Organization) readSection(is));
                }
                skill = new OrganizationSection(organizations);
                break;
            case "Organization":
                String title = is.readUTF();
                String value = is.readUTF();
                String urlBaseInf = value.equals("null") ? null : value;
                Link inf = new Link(title, urlBaseInf);
                int size2 = is.readInt();
                Organization organization = new Organization(inf);
                for (int i = 0; i < size2; i++) {
                    String url = is.readUTF();
                    LocalDate startDate = readDate(is);
                    LocalDate endDate = readDate(is);
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

    public LocalDate readDate(DataInputStream is) throws IOException {
        return LocalDate.of(is.readInt(), is.readInt(), is.readInt());
    }


    //custom map foreach method with throwing Exception
    public <K, V> void forEachWithExMap(Map<K, V> map, KeyValue<K, V> action) throws IOException {
        Objects.requireNonNull(map);
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                throw new ConcurrentModificationException(ise);
            }
            action.loop(k, v);
        }
    }
    //custom Collection foreach method with throwing Exception
    public <T> void forEachWithExList(Collection<T> list, Item<T> action) throws IOException {
        Objects.requireNonNull(list);
        Objects.requireNonNull(action);
        for (T s : list) {
            action.function(s);
        }
    }
}
//custom func interface Customer with throwing Exception
interface Item<T> {
    void function(T t) throws IOException;
}
//custom func interface BiConsumer with throwing Exception
interface KeyValue<K, V> {
    void loop(K k, V v) throws IOException;
}

