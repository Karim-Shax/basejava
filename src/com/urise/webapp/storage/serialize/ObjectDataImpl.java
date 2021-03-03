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
            Map<PersonInf, ProfessionalSkill> info = r.getInfo();
            dos.writeInt(info.size());
            info.entrySet().forEach(x -> {
                try {
                    dos.writeUTF(x.getKey().name());
                    ProfessionalSkill skill = x.getValue();
                    writeModelField(skill, dos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void writeModelField(ProfessionalSkill skill, DataOutputStream dos) throws IOException {
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
            case "Certification":
                Certification certification = (Certification) skill;
                dos.writeInt(certification.getDetail().size());
                for (Experience ex : certification.getDetail()) {
                    writeModelField(ex, dos);
                }
                break;
            case "Experience":
                Experience experience = (Experience) skill;
                dos.writeUTF(experience.getHomePage().getTitle());
                if (experience.getHomePage().getUrlText() == null) {
                    dos.writeUTF("null");
                } else {
                    dos.writeUTF(experience.getHomePage().getUrlText());
                }
                dos.writeInt(experience.getList().size());
                for (Experience.PeriodPosition per : experience.getList()) {
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


    private EnumMap<PersonInf, ProfessionalSkill> readModelMap(DataInputStream is, int size) throws IOException {
        EnumMap<PersonInf, ProfessionalSkill> map = new EnumMap<>(PersonInf.class);
        for (int i = 0; i < size; i++) {
            map.put(PersonInf.valueOf(is.readUTF()), readSkill(is));
        }
        return map;
    }

    private ProfessionalSkill readSkill(DataInputStream is) throws IOException {
        String skillName = is.readUTF();
        ProfessionalSkill skill = null;
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
            case "Certification":
                int size1 = is.readInt();
                List<Experience> experiences = new ArrayList<>();
                for (int i = 0; i < size1; i++) {
                    experiences.add((Experience) readSkill(is));
                }

                skill = new Certification(experiences);
                break;
            case "Experience":
                String title = is.readUTF();
                String urlBaseInf = is.readUTF();
                if (urlBaseInf.equals("null")) {
                    urlBaseInf = null;
                }
                BaseInf inf = new BaseInf(title, urlBaseInf);
                int size2 = is.readInt();
                Experience experience = new Experience(inf);
                for (int i = 0; i < size2; i++) {
                    String url = is.readUTF();
                    LocalDate startDate = LocalDate.of(is.readInt(), is.readInt(), is.readInt());
                    LocalDate endDate = LocalDate.of(is.readInt(), is.readInt(), is.readInt());
                    String techVersion = is.readUTF();
                    if (techVersion.equals("null")) {
                        techVersion = null;
                    }
                    experience.addPeriodPosition(new Experience.PeriodPosition(
                            url,
                            startDate,
                            endDate,
                            techVersion

                    ));
                }
                skill = experience;
                break;
        }
        return skill;
    }

}
