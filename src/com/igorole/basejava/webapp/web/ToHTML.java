package com.igorole.basejava.webapp.web;

import com.igorole.basejava.webapp.model.ContactType;
import com.igorole.basejava.webapp.model.Resume;

import java.util.Map;

public class ToHTML {
    static public String getResumeHTML(Resume resume) {
        String result=""
                ;


        return result;
    }
    static private String getContacts(Resume resume) {

        for (Map.Entry<ContactType, String> entry: resume.getContacts(). entrySet()) {
            System.out.println(entry.getValue());
        }
        return "";
    }
}
