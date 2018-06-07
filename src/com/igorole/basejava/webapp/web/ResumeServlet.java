package com.igorole.basejava.webapp.web;

import com.igorole.basejava.webapp.Config;
import com.igorole.basejava.webapp.model.*;
import com.igorole.basejava.webapp.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ResumeServlet extends HttpServlet {
    SqlStorage storage;


    @Override
    public void init() throws ServletException {
        super.init();
        storage = new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("save") != null) {
            String uuid = request.getParameter("uuid");
            String fullName = request.getParameter("fullName");
            Resume r = storage.get(uuid);
            r.setFullName(fullName);
            for (ContactType type : ContactType.values()) {
                String value = request.getParameter(type.name());
                if (value != null && value.trim().length() != 0) {
                    r.addContact(type, value);
                } else {
                    r.getContacts().remove(type);
                }
            }
            for (SectionType type : SectionType.values()) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        String var = request.getParameter(type.name());
                        if (var != null)
                            r.addSections(type, new TextSection(var));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        String[] vars = request.getParameterValues(type.name());
                        if (vars != null)
                            r.addSections(type, new ListSection(Arrays.asList(vars)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                    //    request.get

                        break;
                }
            }
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");


        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);
    }
}