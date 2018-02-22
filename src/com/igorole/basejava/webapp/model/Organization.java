package com.igorole.basejava.webapp.model;

import java.util.Date;

public class Organization {
    private Link link;
    private Date dateStart;
    private Date dateEnd;
    private String title;
    private String text;

    public Organization(Link link, Date dateStart, Date dateEnd, String title, String text) {
        this.link = link;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.title = title;
        this.text = text;
    }

    public Link getLink() {
        return link;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
