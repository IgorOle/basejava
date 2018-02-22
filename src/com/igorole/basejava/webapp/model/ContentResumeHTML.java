package com.igorole.basejava.webapp.model;

public class ContentResumeHTML {
    private final Resume resume;

    public ContentResumeHTML(Resume resume) {
        this.resume = resume;
    }

    public String getHTMLFullName() {
        return "<bold>" + this.resume.getFullName() + "</bold><p>";
    }

    public String getHtmlContact() {
        StringBuilder result = new StringBuilder();
        for (ContactType type : ContactType.values()) {
            String data = this.resume.getContactByType(type);
            if (data != null)
                result.append(data + "<p>");
        }
        return result.toString();
    }

    public String getHTMLAllSecions() {
        StringBuilder result = new StringBuilder();
        for (SectionType type : SectionType.values()) {
            Section section = this.resume.getSection(type);
            if (section != null) {
                result.append(section.getHTMLTextSection());
            }
        }
        return result.toString();
    }
}
