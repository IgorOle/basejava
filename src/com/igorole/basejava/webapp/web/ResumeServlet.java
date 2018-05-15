package com.igorole.basejava.webapp.web;

import com.igorole.basejava.webapp.Config;
import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    SqlStorage storage;
    List<Resume> resumes;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        resumes = storage.getAllSorted();
        for (Resume resume : resumes) {
            response.getWriter().write(ToHTML.getResumeHTML(resume));
            response.getWriter().write("<hr>");
        }
    }
}