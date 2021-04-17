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
        String action = request.getParameter("save");
        String fullName = request.getParameter("fullName");
        Resume r = null;
        if (fullName == null || fullName.equals("")) {
            response.sendRedirect("resume");
            return;
        }
        if (action.equals("add")) {
            r = new Resume(UUID.randomUUID().toString(), fullName);
            updateResume(request, r);
            storage.save(r);
        } else {
            String uuid = request.getParameter("uuid");
            storage.delete(uuid);
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
        r.addSection(SectionType.PERSONAL, new TextSection(request.getParameter("PERSONAL")));
        r.addSection(SectionType.OBJECTIVE, new TextSection(request.getParameter("OBJECTIVE")));
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
            case "clear":
                storage.clear();
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                url = "/WEB-INF/jsp/view.jsp";
                request.setAttribute("resume", r);
                break;
            case "edit":
                r = storage.get(uuid);
                url = "/WEB-INF/jsp/edit.jsp";
                request.setAttribute("resume", r);
                break;
            case "add":
                url = "/WEB-INF/jsp/add.jsp";
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
        System.out.println(Arrays.toString(exParam));
        if (exParam != null)
            for (int i = 0; i < exParam.length; i += 6) {
                name = exParam[i];
                if (name.isEmpty()) {
                    continue;
                }
                url = exParam[i + 1];
                if (exParam[i + 2] != null && exParam[i + 2].trim().length() != 0) {
                    startTime = LocalDate.parse(exParam[i + 2]);
                }
                if (exParam[i + 3] == null && exParam[i + 3].trim().length() != 0) {
                    endTime = LocalDate.now();
                } else if (exParam[i+3]!=null&&!exParam[i+3].trim().equals("")) {
                    endTime = LocalDate.parse(exParam[i + 3]);
                }
                title = exParam[i + 4];
                description = exParam[i + 5];
                if (name.trim().length() != 0 && startTime != null && title != null && !title.isEmpty()) {
                    organizations.add(new Organization(name, url, startTime, endTime, title, description));
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
                r.addSection(type, new ListSection(list));
            }
        }
    }
}
