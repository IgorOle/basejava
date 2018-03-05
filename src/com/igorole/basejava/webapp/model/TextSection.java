package com.igorole.basejava.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class TextSection implements Section, Serializable {
    private final String content;

    public TextSection(String content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String getHTMLTextSection() {
        return null;
    }
}
