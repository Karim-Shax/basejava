package com.urise.webapp.web;

import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.Config;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage = Config.getConfig().sqlStorage();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("Windows-1251");
        response.setCharacterEncoding("Windows-1251");
        String name = request.getParameter("name");
        PrintWriter writer = response.getWriter();
        List<Resume> list = storage.getAllSortedList();
        if (name == null) {
            writer.println("<html>");
            writer.println("<head>\n" +
                    "    <meta charset=\"UTF-8\" />\n" +
                    "    <title>First JSP App</title>\n" +
                    "</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");
            writer.println(" <tr>\n" +
                    "    <th>Full Name</th>\n" +
                    "    <th>UUID</th>\n" +
                    "   </tr>");
            for (Resume r : storage.getAllSortedList()) {
                writer.println("<tr>\n" +
                        "    <th>" + r.getFullName() + "</th>\n" +
                        "    <th>" + r.getUuid() + "</th>\n" +
                        "   </tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } else {
            writer.println("<html>");
            writer.println("<head>\n" +
                    "    <meta charset=\"UTF-8\" />\n" +
                    "    <title>First JSP App</title>\n" +
                    "</head>");
            writer.println("<body>");
            writer.println("<p>" + storage.get(name).getFullName() + " " + storage.get(name).getUuid() + "</p>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
