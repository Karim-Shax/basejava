package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume first = new Resume("uuid", "full Name");

        List<String> archievement = new ArrayList<>();
        List<String> qualif = new ArrayList<>();
        List<Experience> experience = new ArrayList<>();
        List<Experience> eduction = new ArrayList<>();


        EnumMap<ContentType, BaseInf> contacts = new EnumMap<>(ContentType.class);
        EnumMap<PersonInf, ProfessionalSkill> pesonInf = new EnumMap<>(PersonInf.class);

        contacts.put(ContentType.PHONE, new BaseInf(ContentType.PHONE.getTitle(), "+7(921) 855-0482"));
        contacts.put(ContentType.SKYPE, new BaseInf(ContentType.SKYPE.getTitle(), "grigory.kislin"));
        contacts.put(ContentType.EMAIL, new BaseInf(ContentType.EMAIL.getTitle(), "gkislin@yandex.ru"));
        contacts.put(ContentType.LINKEDIN, new BaseInf(ContentType.LINKEDIN.getTitle(), "https://www.linkedin.com/in/gkislin"));
        contacts.put(ContentType.GITHUB, new BaseInf(ContentType.GITHUB.getTitle(), "https://github.com/gkislin"));
        contacts.put(ContentType.STACKOVERFLOW, new BaseInf(ContentType.STACKOVERFLOW.getTitle(), "https://stackoverflow.com/users/548473/grigory-kislin"));

        first.setContacts(contacts);

        String personal = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры." + "\n";
        String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям" + "\n";


        archievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников." + "\n");
        archievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk." + "\n");
        archievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера." + "\n");
        archievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, " +
                "HTML5, Highstock для алгоритмического трейдинга." + "\n");
        archievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)." + "\n");
        archievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк)," +
                "Белоруcсии(Erip, Osmp) и Никарагуа." + "\n");

        qualif.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2" + "\n");
        qualif.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce" + "\n");
        qualif.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle," + "\n");
        qualif.add("MySQL, SQLite, MS SQL, HSQLDB" + "\n");
        qualif.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy," + "\n");
        qualif.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)." + "\n");
        qualif.add("Python: Django." + "\n");
        qualif.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js" + "\n");
        qualif.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka" + "\n");
        qualif.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, " +
                "HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT." + "\n");
        qualif.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix," + "\n");
        qualif.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer." + "\n");
        qualif.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования" + "\n");
        qualif.add("Родной русский, английский \"upper intermediate\"" + "\n");

        String javaOnline = "\tАвтор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок." + "\n";
        String Wrike = "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO." + "\n";
        String RIT = "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, " +
                "Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python" + "\n";
        String Luxoft = "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                "Реализация RIA-приложения для администрирования," +
                " мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.";
        String Yota = "\tВедущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, " +
                "статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)" + "\n";
        String Ekarta = "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)." + "\n";
        String Siemens = "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)." + "\n";
        String Alcatel = "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)." + "\n";

        experience.add(new Experience("Java Online Projects", LocalDate.of(2013, 10, 1), LocalDate.now(), javaOnline));
        experience.add(new Experience("Wrike", LocalDate.of(2014, 10, 1), LocalDate.of(16, 1, 1), Wrike));
        experience.add(new Experience("RIT Center", LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), RIT));
        experience.add(new Experience("Luxoft (Deutsche Bank)", LocalDate.of(2010, 12, 1), LocalDate.of(2012, 04, 01), Luxoft));
        experience.add(new Experience("Yota", LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), Yota));
        experience.add(new Experience("Enkata", LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), Ekarta));
        experience.add(new Experience("Siemens AG", LocalDate.of(2005, 1, 1), LocalDate.of(1997, 9, 1), Siemens));
        experience.add(new Experience("Alcatel", LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), Alcatel));

        String coursera = "\"Functional Programming Principles in Scala\" by Martin Odersky";
        String LuxEdu = "\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"";
        String SiemensEdu = "\t3 месяца обучения мобильным IN сетям (Берлин)";
        String AlcatelEdu = "\t6 месяцев обучения цифровым телефонным сетям (Москва)";
        String spnuit = "Аспирантура (программист С, С++)";
        String spnuit1 = "Инженер (программист Fortran, C)";
        String MFTI = "Закончил с отличием";

        eduction.add(new Experience("Coursera", LocalDate.of(2005, 1, 1), LocalDate.of(2013, 5, 1), coursera));
        eduction.add(new Experience("Luxoft", LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), LuxEdu));
        eduction.add(new Experience("SPNUIT", LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), spnuit));
        eduction.add(new Experience("SIEMENS", LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), SiemensEdu));
        eduction.add(new Experience("ALCATEL", LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), AlcatelEdu));
        eduction.add(new Experience("SPNUIT", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), spnuit1));
        eduction.add(new Experience("MFTI", LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), MFTI));

        pesonInf.put(PersonInf.PERSONAL, new BaseInf(PersonInf.PERSONAL.getTitle(), personal));
        pesonInf.put(PersonInf.OBJECTIVE, new BaseInf(PersonInf.OBJECTIVE.getTitle(), objective));
        pesonInf.put(PersonInf.ACHIEVEMENT, new Certification(PersonInf.ACHIEVEMENT.getTitle(), archievement));
        pesonInf.put(PersonInf.QUALIFICATIONS, new Certification(PersonInf.QUALIFICATIONS.getTitle(), qualif));
        pesonInf.put(PersonInf.EXPERIENCE, new Certification(PersonInf.EXPERIENCE.getTitle(), experience));
        pesonInf.put(PersonInf.EDUCATION, new Certification(PersonInf.EDUCATION.getTitle(), eduction));

        first.setInfo(pesonInf);

        for (EnumMap.Entry<ContentType, BaseInf> ob : first.getContacts().entrySet()) {
            System.out.println(ob.getValue().toString());
        }

        for (EnumMap.Entry<PersonInf, ProfessionalSkill> ob : first.getInfo().entrySet()) {
            System.out.println(ob.getValue().toString());
        }

    }
}
