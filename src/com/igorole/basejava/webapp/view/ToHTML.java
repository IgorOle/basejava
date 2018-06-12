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

    public static String getFullName(Resume resume) {
        return "<tr><th colspan=2>" + resume.getFullName() + "</th><tr>";
    }

    public static String getContacts(Resume resume) {
        StringBuffer result = new StringBuffer();
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            result.append("<tr><th>" + entry.getKey().getTitle() + "</th><td>" + entry.getValue() + "</td></tr>");
        }
        return result.toString();
    }

    public static String getSections(Resume resume) {
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

    public static String getSectionInputTag(SectionType type, Section section, boolean js) {
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
                res = getOrganizationSectionEdit(type, section, js) + "<hr>";
                break;
        }
        return res;
    }

    public static String getContact(Resume resume, ContactType type) {
        return resume.getContact(type);
    }

    public static String getOrganizationSectionEdit(SectionType type, Section section, boolean js) {
        // boolean js - is flag of generate numElement by javascript or by html
        String begin = "<div class='bs-callout bs-callout-warning'>", end = "</div>";
        if (js || section == null) {
            return begin
                    + getOrganizationInputEdit(type.name(), null, 0, js)
                    + getActivityInputEdit(type.name(), null, 0)
                    + end;
        }
        StringBuffer res = new StringBuffer();
        int i = 0;
        for (Organization organization : ((OrganizationSection) section).getOrganizations()) {
            res.append(getOrganizationInputEdit(type.name(), organization, i, false));
            for (Organization.Activity activity : organization.getActivities()) {
                res.append(getActivityInputEdit(type.name(), activity, i));
            }
            i++;
        }
        return begin + res.toString() + end;
    }

    private static String getTextSection(SectionType type, Section section) {
        return "<tr><td colspan=2><b>" + SectionType.valueOf(type.name()).getTitle() + "</b>"
                + "<br>" + section + "</td></tr>";
    }

    private static String getListSection(SectionType type, Section section) {
        StringBuffer result = new StringBuffer();
        result.append("<tr><td colspan=2><b>" + type.getTitle());
        result.append("<ul>");
        result.append(((ListSection) section).getItems().stream().map((s) -> "<li>" + s).collect(Collectors.joining()));
        result.append("</ul>");
        return result.toString() + "</td></tr>";
    }

    private static String getTextSectionEdit(SectionType type, Section section) {
        return getInputEdit(type, section == null ? "" : section.toString());
    }

    private static String getListSectionEdit(SectionType type, Section section) {
        if (section == null) return getInputEdit(type, "");

        StringBuffer res = new StringBuffer();
        for (String value : ((ListSection) section).getItems()) {
            res.append(getInputEdit(type, value));
        }
        return res.toString();
    }

    private static String getInputEdit(SectionType type, String value) {
        if (value != "")
            value = "' value='" + value;
        return "<input type='text' name='" + type.name() + value + "' class='form-control' placeholder='" + type.getTitle() + "'><br>";
    }



    private static String getOrganizationInputEdit(String type, Organization organization, Integer numElement, boolean js) {
        String Name = "", URL = "";
        if (organization != null) {
            Name = organization.getName();
            URL = organization.getUrl();
        }
        return
                "<div class='form-group col-xs-12'>" +
                        "   <label class='control-label col-xs-1 "+type+"'>Организация</label>" +
                        "   <input type='text' " +
                        "       name='" + type + "_Org_" + ((js) ? "\"+inc('" + type + "', 1)+\"' " : numElement.toString().trim() + "'") + //increment accounter
                        "       class='form-control col-sm-10' value='" + Name + "' placeholder='Название организации'>" +
                        "</div>" +
                        "<div class='form-group col-xs-12'>" +
                        "   <label class='control-label col-xs-1'>URL</label>" +
                        "   <input type='text' " +
                        "       name='" + type + "_URL_" + ((js) ? "\"+inc('" + type + "', 0)+\"' " : numElement.toString().trim() + "'") +
                        "   class='form-control col-sm-10' value='" + URL + "' placeholder='http://...'>" +
                        "</div>" +
                        "<div>" +
                        "<a href='' class='clsActivity' id='add_" + type + "_Activity'" + //button for add activity; name is value for elements: dateStart, dateEnd, Describe
                        "           name=" + ((js) ? "\"+inc('" + type + "', 0)+\"' " : numElement.toString().trim() + "'") + ">" +
                        "   <i class='glyphicon glyphicon-plus-sign'></i>" +
                        "</a>" +
                        "</div>"
                ;
    }

    public static String getActivityInputEdit(String type, Organization.Activity activity, Integer numElement) {
        String dateStart = "", dateEnd = "", desc = "";
        if (activity != null) {
            dateStart = " value='" + activity.getStartDate().toString() + "'";
            dateEnd = " value='" + activity.getEndDate().toString() + "'";
            desc = activity.getDescription();
        }
        return "<div class='form-inline'>" +
                "   <div class='form-group col-xs-12'>" +
                "       <label class='control-label col-sm-1'>С</label>" +
                "       <input type='date' name='" + type + "_DateStart_" + numElement.toString().trim() + "'" + dateStart + " class='form-control col-sm-1'>" +
                "       <label class='control-label col-sm-1'>По</label>" +
                "       <input type='date' name='" + type + "_DateEnd_" + numElement.toString().trim() + "'" + dateEnd + " class='form-control col-sm-1'>" +
                "   </div>" +
                "</div>" +
                "<div class='form-group col-xs-12'>" +
                "       <label class='control-label col-xs-1'>Описание</label>" +
                "       <textarea name='" + type + "_Descr_" + numElement.toString().trim() + "'" + " class='form-control col-sm-11' placeholder='Описания' >" + desc + "</textarea>" +
                "</div>" +
                "<hr>";
    }

}
