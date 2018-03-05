package com.igorole.basejava.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    private String name;
    private String url;


    public Link(String name, String url) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(url);
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
