package com.igorole.basejava.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrganizationSection implements Section {
    ArrayList<Organization> organizations;
    private final String title;

    public OrganizationSection(String title, ArrayList<Organization> organizations) {
        this.organizations = organizations;
        this.title = title;
    }

    public String getHTMLTextSection() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        StringBuilder result = new StringBuilder();
        result.append("<bold>" + title + "</bold>" + "<p>");
        result.append("<table>");
        for (Organization organization : organizations) {
            result.append("<tr><td colspan=2>" + organization.getLink().getHTMLText() + "</td></tr><p>");
            result.append("<tr><td>" + organization.getDateStart().format(dtf) + " - " + organization.getDateEnd().format(dtf) + "</td>"
                    + "<td>" + organization.getTitle() + "<p>" + organization.getText() + "</td></tr>");
        }
        result.append("</table>");
        return result.toString();
    }
}

