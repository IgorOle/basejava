package com.igorole.basejava.webapp.web;

import com.igorole.basejava.webapp.Config;
import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.SqlStorage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    SqlStorage storage;
    Resume resume;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
        if(uuid != null) {
            storage = new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword());
            resume = storage.get(uuid);
            response.getWriter().write(ToHTML.getResumeHTML(resume));
        }
    }
}