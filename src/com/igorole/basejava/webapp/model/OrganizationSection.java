package com.igorole.basejava.webapp.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class OrganizationSection implements Section {
    ArrayList<Organization> organizations;
    private final String title;

    public OrganizationSection(String title, ArrayList<Organization> organizations) {
        this.organizations = organizations;
        this.title = title;
    }

    public String getHTMLTextSection() {
        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(tz);

        StringBuilder result = new StringBuilder();
        result.append("<bold>" + title + "</bold>" + "<p>");
        result.append("<table>");
        for (Organization organization : organizations) {
            result.append("<tr><td colspan=2>" + organization.getLink().getHTMLText() + "</td></tr><p>");
            result.append("<tr><td>" + dateFormat.format(organization.getDateStart()) + " - " + dateFormat.format(organization.getDateEnd()) + "</td>"
                    + "<td>" + organization.getTitle() + "<p>" + organization.getText() + "</td></tr>");
        }
        result.append("</table>");
        return result.toString();
    }
}

