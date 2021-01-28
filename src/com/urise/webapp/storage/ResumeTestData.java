package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume first = new Resume("uuid", "full Name");

        List<String> archievement = new ArrayList<>();
        List<String> qualif = new ArrayList<>();
        Map<String, ExperienceEducationInf> experienceInf = new HashMap<>();
        Map<String, ExperienceEducationInf> eduExperienceInf = new HashMap<>();


        EnumMap<Contacts, BaseInf> contacts = new EnumMap<>(Contacts.class);
        EnumMap<PersonInf, ProfessionalSkill> pesonInf = new EnumMap<PersonInf, ProfessionalSkill>(PersonInf.class);

        contacts.put(Contacts.PHONE, new BaseInf(Contacts.PHONE.getTitle(), "+7(921) 855-0482"));
        contacts.put(Contacts.SKYPE, new BaseInf(Contacts.SKYPE.getTitle(), "grigory.kislin"));
        contacts.put(Contacts.EMAIL, new BaseInf(Contacts.EMAIL.getTitle(), "gkislin@yandex.ru"));
        contacts.put(Contacts.LINKEDIN, new BaseInf(Contacts.LINKEDIN.getTitle(), "https://www.linkedin.com/in/gkislin"));
        contacts.put(Contacts.GITHUB, new BaseInf(Contacts.GITHUB.getTitle(), "https://github.com/gkislin"));
        contacts.put(Contacts.STACKOVERFLOW, new BaseInf(Contacts.STACKOVERFLOW.getTitle(), "https://stackoverflow.com/users/548473/grigory-kislin"));

        first.setContacts(contacts);

        String personal = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
        String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";


        archievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        archievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        archievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        archievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, " +
                "HTML5, Highstock для алгоритмического трейдинга.");
        archievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        archievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк)," +
                "Белоруcсии(Erip, Osmp) и Никарагуа.");

        qualif.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualif.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualif.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualif.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualif.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualif.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualif.add("Python: Django.");
        qualif.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualif.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualif.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, " +
                "HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualif.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualif.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualif.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualif.add("Родной русский, английский \"upper intermediate\"");

        String javaOnline = "\tАвтор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.";
        String Wrike = "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        String RIT = "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, " +
                "Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        String Luxoft = "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                "Реализация RIA-приложения для администрирования," +
                " мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.";
        String Yota = "\tВедущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, " +
                "статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)";
        String Ekarta = "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).";
        String Siemens = "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).";
        String Alcatel = "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).";

        experienceInf.put("Java Online Projects", new ExperienceEducationInf("Java Online Projects", LocalDate.of(2013, 10, 1), LocalDate.now(), javaOnline));
        experienceInf.put("Wrike", new ExperienceEducationInf("Wrike", LocalDate.of(2014, 10, 1), LocalDate.of(16, 1, 1), Wrike));
        experienceInf.put("RIT Center", new ExperienceEducationInf("RIT Center", LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), RIT));
        experienceInf.put("Luxoft (Deutsche Bank)", new ExperienceEducationInf("Luxoft (Deutsche Bank)", LocalDate.of(2010, 12, 1), LocalDate.of(2012, 04, 01), Luxoft));
        experienceInf.put("Yota", new ExperienceEducationInf("Yota", LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), Yota));
        experienceInf.put("Enkata", new ExperienceEducationInf("Enkata", LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), Ekarta));
        experienceInf.put("Siemens AG", new ExperienceEducationInf("Siemens AG", LocalDate.of(2005, 1, 1), LocalDate.of(1997, 9, 1), Siemens));
        experienceInf.put("Alcatel", new ExperienceEducationInf("Alcatel", LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), Alcatel));

        String coursera = "\"Functional Programming Principles in Scala\" by Martin Odersky";
        String LuxEdu = "\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"";
        String SiemensEdu = "\t3 месяца обучения мобильным IN сетям (Берлин)";
        String AlcatelEdu = "\t6 месяцев обучения цифровым телефонным сетям (Москва)";
        String spnuit = "Аспирантура (программист С, С++)";
        String spnuit1 = "Инженер (программист Fortran, C)";
        String MFTI = "Закончил с отличием";

        eduExperienceInf.put("Coursera", new ExperienceEducationInf("Coursera", LocalDate.of(2005, 1, 1), LocalDate.of(2013, 5, 1), coursera));
        eduExperienceInf.put("Luxoft", new ExperienceEducationInf("Luxoft", LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), LuxEdu));
        eduExperienceInf.put("SPNUIT", new ExperienceEducationInf("SPNUIT", LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), spnuit));
        eduExperienceInf.put("SIEMENS", new ExperienceEducationInf("SIEMENS", LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), SiemensEdu));
        eduExperienceInf.put("ALCATEL", new ExperienceEducationInf("ALCATEL", LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), AlcatelEdu));
        eduExperienceInf.put("SPNUIT1", new ExperienceEducationInf("SPNUIT", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), spnuit1));
        eduExperienceInf.put("MFTI", new ExperienceEducationInf("MFTI", LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), MFTI));

        pesonInf.put(PersonInf.PERSONAL, new BaseInf(PersonInf.PERSONAL.getTitle(), personal));
        pesonInf.put(PersonInf.OBJECTIVE, new BaseInf(PersonInf.OBJECTIVE.getTitle(), objective));
        pesonInf.put(PersonInf.ACHIEVEMENT, new Certification(PersonInf.ACHIEVEMENT.getTitle(), archievement));
        pesonInf.put(PersonInf.QUALIFICATIONS, new Certification(PersonInf.QUALIFICATIONS.getTitle(), qualif));
        pesonInf.put(PersonInf.EXPERIENCE, new TechExperience(PersonInf.EXPERIENCE.getTitle(), experienceInf));
        pesonInf.put(PersonInf.EDUCATION, new TechExperience(PersonInf.EDUCATION.getTitle(), eduExperienceInf));

        first.setInfo(pesonInf);

        for (EnumMap.Entry<Contacts, BaseInf> ob : first.getContacts().entrySet()) {
            System.out.println(ob.getValue().toString()+"\n");
        }

        for (EnumMap.Entry<PersonInf, ProfessionalSkill> ob : first.getInfo().entrySet()) {
            System.out.println(ob.getValue().toString()+"\n");
        }

    }
}
