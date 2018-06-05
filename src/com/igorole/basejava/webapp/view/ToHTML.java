package com.igorole.basejava.webapp.view;

import com.igorole.basejava.webapp.model.*;

import java.util.Map;
import java.util.stream.Collectors;

public class ToHTML {

    static public String getResumeHTML(Resume resume) {
        return "<table>"
                + getFullName(resume)
                + getContacts(resume)
                + getSections(resume)
                + "</table>";
    }

    static public String getFullName(Resume resume) {
        return "<tr><th colspan=2>" + resume.getFullName() + "</th><tr>";
    }

    static public String getContacts(Resume resume) {
        StringBuffer result = new StringBuffer();
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            result.append("<tr><th>" + entry.getKey().getTitle() + "</th><td>" + entry.getValue() + "</td></tr>");
        }
        return result.toString();
    }

    static public String getSections(Resume resume) {
        StringBuffer result = new StringBuffer();
        for (Map.Entry<SectionType, Section> section : resume.getSections().entrySet()) {
            switch (section.getKey()) {
                case PERSONAL:
                case OBJECTIVE:
                    result.append(getTextSection(section.getKey(), section.getValue()));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    result.append(getListSection(section.getKey(), section.getValue()));
                    break;
            }
        }
        return result.toString();
    }

    static public String getSectionInputTag(SectionType type, Section section) {
        String res = "";
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                res = getTextSectionEdit(type, section);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                res = getListSectionEdit(type, section);
                break;
            case EDUCATION:
            case EXPERIENCE:
                res = getOrganizationSectionEdit(type, section);
                break;
        }
        return res;
    }

    public static String getContact(Resume resume, ContactType type) {
        return resume.getContact(type);
    }

    static private String getTextSection(SectionType type, Section section) {
        return "<tr><td colspan=2><b>" + SectionType.valueOf(type.name()).getTitle() + "</b>"
                + "<br>" + section + "</td></tr>";
    }

    static private String getListSection(SectionType type, Section section) {
        StringBuffer result = new StringBuffer();
        result.append("<tr><td colspan=2><b>" + type.getTitle());
        result.append("<ul>");
        result.append(((ListSection) section).getItems().stream().map((s) -> "<li>" + s).collect(Collectors.joining()));
        result.append("</ul>");
        return result.toString() + "</td></tr>";
    }

    static private String getTextSectionEdit(SectionType type, Section section) {
        return getInputEdit(type, section == null ? "" : section.toString());
    }

    static private String getListSectionEdit(SectionType type, Section section) {
        if (section == null) return getInputEdit(type, "");

        StringBuffer res = new StringBuffer();
        for (String value : ((ListSection) section).getItems()) {
            res.append(getInputEdit(type, value));
        }
        return res.toString();
    }

    static private String getInputEdit(SectionType type, String value) {
        if (value != "")
            value = "' value='" + value;
        return "<input type='text' name='" + type.name() + value + "' class='form-control' placeholder='" + type.getTitle() + "'><br>";
    }

    private static String getOrganizationSectionEdit(SectionType type, Section section) {
        StringBuffer res = new StringBuffer();
        if (section == null)
            return (getOrganizationInputEdit(type, null, 0)
                    + getActivityInputEdit(type, null, null)
                    //+ getAccounterInputTag(type, 0)
            );
        int i = 0, j = 0;

        for (Organization organization : ((OrganizationSection) section).getOrganizations()) {
            res.append(getOrganizationInputEdit(type, organization, i++));
            for (Organization.Activity activity : organization.getActivities()) {
                res.append(getActivityInputEdit(type, activity, j++));
            }
        }
        res.append(getAccounterInputTag(type, ((OrganizationSection) section).getOrganizations().size()));
        return res.toString();
    }

    private static String getOrganizationInputEdit(SectionType type, Organization organization, Integer num) {
        String Name = "", URL = "";
        if (organization != null) {
            Name = organization.getName();
            URL = organization.getUrl();
        }
        return
                "<div class='form-group col-xs-12'>" +
                        "       <label class='control-label col-xs-1'>Организация</label>" +
                        "       <input type='text' " +
                        "               name='" + type.name() + "_Org_" + ( (organization == null )?"\"+inc('" + type.name() + "', 1)+\"' ": "'" + num.toString() + "'") +
                        "               class='form-control col-sm-10' value='" + Name + "' placeholder='" + type.getTitle() + "'>" +
                        "   </div>" +
                        "   <div class='form-group col-xs-12'>" +
                        "       <label class='control-label col-xs-1'>URL</label>" +
                        "       <input type='text' " +
                        "               name='" + type.name() + "_URL_" + ( (organization == null )?"\"+inc('" + type.name() + "', 0)+\"' ": "'" + num.toString() + "'") +
                        "       class='form-control col-sm-10' value='" + URL + "' placeholder='http://...'>" +
                        "   </div>";
    }

    private static String getActivityInputEdit(SectionType type, Organization.Activity activity, Integer num) {
        String dateStartVal, dateEndVal, descrVal;
        dateStartVal = (activity == null) ? "" : " value='" + activity.getStartDate().toString() + "'";
        dateEndVal = (activity == null) ? "" : " value='" + activity.getEndDate().toString() + "'";
        descrVal = (activity == null) ? "" : " value='" + activity.getDescription() + "'";

        return "<div class='form-inline'>" +
                "   <div class='form-group col-xs-12'>" +
                "       <label class='control-label col-sm-1'>С</label>" +
                "       <input type='date' name='" + type.name() + "DateStart" + dateStartVal + "' class='form-control col-sm-1'>" +
                "       <label class='control-label col-sm-1'>По</label>" +
                "       <input type='date' name='" + type.name() + "DateEnd" + dateEndVal + "' class='form-control col-sm-1'>" +
                "   </div>" +
                "</div>" +
                "<div class='form-group col-xs-12'>" +
                "       <label class='control-label col-xs-1'>Описание</label>" +
                "       <textarea name='" + type.name() + "Descr' class='form-control col-sm-11' placeholder='Описания' >" + descrVal + "</textarea>" +
                "</div>" +
                "";
    }

    private static String getAccounterInputTag(SectionType type, Integer cnt) {
        return "<input type='hidden' name=yyy id='" + type.name() + "' value='" + cnt.toString() + "'>";
    }
}
