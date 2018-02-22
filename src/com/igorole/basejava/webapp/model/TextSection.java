package com.igorole.basejava.webapp.model;

import java.util.Objects;

public class TextSection implements Section {
    private final String data;
    private final String title;

    public TextSection(String title, String data) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(data);
        this.data = data;
        this.title = title;
    }

    public String getHTMLTextSection() {
        return "<bold>" + title + "</bold>" + "<p>" + data + "<p>";
    }
}
