package com.igorole.basejava.webapp.model;

import java.util.ArrayList;

public class ListSection implements Section {
    private final String title;
    private ArrayList<String> stringList = new ArrayList<>();

    public ListSection(String title, ArrayList<String> stringList) {
        this.title = title;
        this.stringList = stringList;
    }

    public String getHTMLTextSection() {
        StringBuilder result = new StringBuilder();
        result.append("<bold>" + title + "</bold>" + "<p>");
        result.append("<ul>");
        for (String string : stringList) {
            result.append("<li>" + string + "</li><p>");
        }
        result.append("</ul>");
        return result.toString();
    }
}
