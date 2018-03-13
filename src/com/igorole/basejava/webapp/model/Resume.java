package com.igorole.basejava.webapp.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    private final String uuid;
    private final String fullName;
    private EnumMap<ContactType, String> contactData = new EnumMap<>(ContactType.class);
    private EnumMap<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid);
        Objects.requireNonNull(fullName);
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactByType(ContactType type) {
        return contactData.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSections(SectionType type, Section sections) {
        this.sections.put(type, sections);
    }

    public void setContactData(ContactType type, String contactData) {
        this.contactData.put(type, contactData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        int res = fullName.compareTo(o.getFullName());
        return res == 0 ? uuid.compareTo(o.getUuid()) : res;
    }
}
