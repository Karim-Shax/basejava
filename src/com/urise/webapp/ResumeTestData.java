package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {

    }

    public static Resume defaultResume(String uuid, String fullName) {

        Resume first = new Resume(uuid, fullName);

        List<String> archievement = new ArrayList<>();
        List<String> qualif = new ArrayList<>();
        List<Organization> organization = new ArrayList<>();
        List<Organization> eduction = new ArrayList<>();


        EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        EnumMap<SectionType, Section> pesonInf = new EnumMap<>(SectionType.class);

        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");

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

        String javaOnline = "Создание, организация и проведение Java онлайн проектов и стажировок." + "\n";
        String Wrike = "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO." + "\n";
        String RIT = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, " +
                "Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python" + "\n";
        String Luxoft = "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                "Реализация RIA-приложения для администрирования," +
                " мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.\n";
        String Yota = "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, " +
                "статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)" + "\n";
        String Ekarta = "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)." + "\n";
        String Siemens = "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)." + "\n";
        String Alcatel = "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)." + "\n";

        organization.add(new Organization("Java Online Projects", null, LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта.\n", javaOnline));
        organization.add(new Organization("Wrike", null, LocalDate.of(2014, 10, 1), LocalDate.of(16, 1, 1), "Старший разработчик (backend)\n", Wrike));
        organization.add(new Organization("RIT Center", null, LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "Java архитектор\n", RIT));
        organization.add(new Organization("Luxoft (Deutsche Bank)", null, LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1), "Ведущий программист\n", Luxoft));
        organization.add(new Organization("Yota", null, LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), "Ведущий специалист\n", Yota));
        organization.add(new Organization("Enkata", null, LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), "Разработчик ПО\n", Ekarta));
        organization.add(new Organization("Siemens AG", null, LocalDate.of(2005, 1, 1), LocalDate.of(1997, 9, 1), "Разработчик ПО\n", Siemens));
        organization.add(new Organization("Alcatel", null, LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию\n", Alcatel));

        String coursera = "Functional Programming Principles in Scala by Martin Odersky";
        String LuxEdu = "Курс Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.";
        String SiemensEdu = "3 месяца обучения мобильным IN сетям (Берлин)";
        String AlcatelEdu = "6 месяцев обучения цифровым телефонным сетям (Москва)";
        String spnuit = "Аспирантура (программист С, С++)";
        String spnuit1 = "Инженер (программист Fortran, C)";
        String MFTI = "Закончил с отличием";

        eduction.add(new Organization("Coursera", null, LocalDate.of(2005, 1, 1), LocalDate.of(2013, 5, 1), coursera, null));
        eduction.add(new Organization("Luxoft", null, LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), LuxEdu, null));
        eduction.add(new Organization("SPNUIT", null, LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), spnuit, null));
        eduction.add(new Organization("SIEMENS", null, LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), SiemensEdu, null));
        eduction.add(new Organization("ALCATEL", null, LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), AlcatelEdu, null));
        eduction.add(new Organization("SPNUIT", null, LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), spnuit1, null));
        eduction.add(new Organization("MFTI", null, LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), MFTI, null));

        pesonInf.put(SectionType.PERSONAL, new TextSection(personal));
        pesonInf.put(SectionType.OBJECTIVE, new TextSection(objective));
        pesonInf.put(SectionType.ACHIEVEMENT, new ListSection(archievement));
        pesonInf.put(SectionType.QUALIFICATIONS, new ListSection(qualif));
        pesonInf.put(SectionType.EXPERIENCE, new OrganizationSection(organization));
        pesonInf.put(SectionType.EDUCATION, new OrganizationSection(eduction));


        first.setInfo(pesonInf);
        return first;
    }
}
