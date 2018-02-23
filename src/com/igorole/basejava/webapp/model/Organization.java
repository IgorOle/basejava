package com.igorole.basejava.webapp.model;

import java.time.LocalDate;

public class Organization {
    private Link link;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String title;
    private String text;

    public Organization(Link link, LocalDate dateStart, LocalDate dateEnd, String title, String text) {
        this.link = link;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.title = title;
        this.text = text;
    }

    public Link getLink() {
        return link;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
