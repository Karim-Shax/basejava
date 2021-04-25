package com.urise.webapp.web;

import com.urise.webapp.model.*;
import com.urise.webapp.sql.Config;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@WebServlet("/resume")
public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init() throws ServletException {
        storage = Config.getConfig().sqlStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullName");
        String uuid = request.getParameter("uuid");
        Resume r = null;
        if (fullName == null || fullName.equals("")) {
            response.sendRedirect("resume");
            return;
        }
        if (!uuid.equals("new") && !uuid.equals("")) {
            r = new Resume(uuid, fullName);
            updateResume(request, r);
            storage.update(r);
        } else if (uuid.equals("new")) {
            r = new Resume(UUID.randomUUID().toString(), fullName);
            updateResume(request, r);
            storage.save(r);
        }
        response.sendRedirect("resume");
    }

    private void updateResume(HttpServletRequest request, Resume r) {
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        addListAndTextSection(request, SectionType.PERSONAL, r);
        addListAndTextSection(request, SectionType.OBJECTIVE, r);
        addListAndTextSection(request, SectionType.ACHIEVEMENT, r);
        addListAndTextSection(request, SectionType.QUALIFICATIONS, r);
        addOrUpdateResume(r, request, SectionType.EDUCATION);
        addOrUpdateResume(r, request, SectionType.EXPERIENCE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSortedList());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        String url = "/WEB-INF/jsp/list.jsp";
        Resume r = new Resume();
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                url = "/WEB-INF/jsp/view.jsp";
                request.setAttribute("resume", r);
                break;
            case "edit":
                if (uuid.equals("new")) {
                    r = new Resume();
                    r.setUuid("new");
                } else {
                    r = storage.get(uuid);
                }
                url = "/WEB-INF/jsp/edit.jsp";
                request.setAttribute("resume", r);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.getRequestDispatcher((url)).forward(request, response);
    }

    private void addOrUpdateResume(Resume r, HttpServletRequest request, SectionType type) {
        String name = null;
        String url = null;
        LocalDate startTime = null;
        LocalDate endTime = null;
        String title = null;
        String description = null;
        String[] exParam = request.getParameterValues(type.name());
        List<Organization> organizations = new ArrayList<>();
        Organization organization = null;
        if (exParam != null) {
            for (int i = 0; i < exParam.length; ) {
                name = exParam[i];
                if (i == exParam.length - 1 || name == null || name.equals("")) {
                    break;
                }
                if (organization != null && !exParam[i].equals("end")) {
                    if (exParam[i] != null && !exParam[i].equals("")) {
                        startTime = LocalDate.parse(exParam[i]);
                    }
                    if (exParam[i + 1] == null || Objects.equals(exParam[i + 1], "")) {
                        endTime = LocalDate.now();
                    } else if (exParam[i + 1] != null && !exParam[i + 1].equals("")) {
                        endTime = LocalDate.parse(exParam[i + 1]);
                    }
                    title = exParam[i + 2];
                    description = exParam[i + 3];
                    if (startTime != null && title != null && !title.isEmpty()) {
                        organization.addPeriodPosition(new Organization.Period(title, startTime, endTime, textView(description)));
                        i += 4;
                    }
                    if (exParam[i].equals("end")) {
                        organizations.add(organization);
                        organization = null;
                    }
                    continue;
                } else if (name.equals("end")) {
                    name = exParam[i + 1];
                    i += 1;
                }
                url = exParam[i + 1];
                if (exParam[i + 2] != null && !exParam[i + 2].equals("")) {
                    startTime = LocalDate.parse(exParam[i + 2]);
                }
                if (exParam[i + 3] == null || Objects.equals(exParam[i + 3], "")) {
                    endTime = LocalDate.now();
                } else if (exParam[i + 3] != null && !exParam[i + 3].equals("")) {
                    endTime = LocalDate.parse(exParam[i + 3]);
                }
                title = exParam[i + 4];
                description = exParam[i + 5];
                if (name.trim().length() != 0 && startTime != null && title != null && !title.isEmpty()) {
                    organization = new Organization(new Link(name, url));
                    organization.addPeriodPosition(new Organization.Period(title + "\n", startTime, endTime, textView(description)));
                }
                i += 6;
                if (exParam[i].equals("end")) {
                    organizations.add(organization);
                    organization = null;
                }
            }
        }
        if (!organizations.isEmpty()) {
            OrganizationSection organizationSection = new OrganizationSection(organizations);
            r.addSection(type, organizationSection);
        }
    }

    private void addListAndTextSection(HttpServletRequest request, SectionType type, Resume r) {
        List<String> list = new ArrayList<>();
        if (request.getParameterValues(type.name()) != null) {
            for (String s : request.getParameterValues(type.name())) {
                if (s != null && s.trim().length() != 0) {
                    list.add(s);
                }
            }
            if (!list.isEmpty()) {
                if (type != SectionType.PERSONAL && type != SectionType.OBJECTIVE) {
                    r.addSection(type, new ListSection(list));
                } else {
                    StringBuilder builder = new StringBuilder();
                    list.stream().forEach((x) -> {
                        builder.append(x + ",");
                    });
                    r.addSection(type, new TextSection(builder.toString()));
                }
            }
        }
    }

    private String textView(String str) {
        StringBuilder builder=new StringBuilder();
        int strLen=70;
        for (int i=0;i<str.length();i++) {
            builder.append(str.charAt(i));
            if (i==strLen){
                builder.append("\n");
                strLen+=70;
            }
        }
        return builder.toString();
    }
}
