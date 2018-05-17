package com.igorole.basejava.webapp.view;

import com.igorole.basejava.webapp.model.*;

import java.util.Map;
import java.util.stream.Collectors;

public class ToHTML {
    static public String getResumeHTML(Resume resume) {
        return "<table>"
                + getFullName(resume)
                + getContacts(resume)
                + getSections(resume)
                + "</table>";
    }

    static public String getFullName(Resume resume) {
        return "<tr><th colspan=2>" + resume.getFullName() + "</th><tr>";
    }

    static public String getContacts(Resume resume) {
        StringBuffer result = new StringBuffer();
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            result.append("<tr><th>" + entry.getKey().getTitle() + "</th><td>" + entry.getValue() + "</td></tr>");
        }
        return result.toString();
    }

    static public String getSections(Resume resume) {
        StringBuffer result = new StringBuffer();

        for (Map.Entry<SectionType, Section> section : resume.getSections().entrySet()) {

            switch (section.getKey()) {
                case PERSONAL:
                case OBJECTIVE:
                    result.append(getTextSection(section.getKey(), section.getValue()));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    result.append(getListSection(section.getKey(), section.getValue()));
                    break;
            }
        }
        return result.toString();
    }

    static private String getTextSection(SectionType type, Section section) {
        return "<tr><td colspan=2><b>" + SectionType.valueOf(type.name()).getTitle() + "</b>"
                + "<br>" + section + "</td></tr>";
    }

    static private String getListSection(SectionType type, Section section) {
        StringBuffer result = new StringBuffer();
        result.append("<tr><td colspan=2><b>" + type.getTitle());
        result.append("<ul>");
        result.append(((ListSection) section).getItems().stream().map((s) -> "<li>" + s).collect(Collectors.joining()));
        result.append("</ul>");
        return result.toString() + "</td></tr>";
    }

}
