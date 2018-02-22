package com.igorole.basejava.webapp;


import com.igorole.basejava.webapp.model.*;
import com.igorole.basejava.webapp.view.OutHTMLResume;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestResume {

    public static void main(String[] args) throws ParseException {
        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(tz);
        OrganizationSection organizationEducationSection;
        OrganizationSection organizationJobSection;
        ListSection listAchiSection;
        ListSection listQualificationSection;
        TextSection textPersonalSection;
        TextSection textObjectiveSection;
        Organization educationOrg1;
        Organization educationOrg2;
        Organization jobOrg1;
        Organization jobOrg2;
        ArrayList<Organization> organizationsEducation = new ArrayList<>();
        ArrayList<Organization> organizationsJobEducation = new ArrayList<>();
        ArrayList<String> achievement = new ArrayList<>();
        ArrayList<String> qualification = new ArrayList<>();
        Resume resume = new Resume("1", "Иванов Иван Иванович");
        //Education
        educationOrg1 = new Organization(new Link("РГУ", "http://rgu.ru"), dateFormat.parse("01-01-1990"), dateFormat.parse("01-01-1995"), "", "Курс молодого бойца1");
        educationOrg2 = new Organization(new Link("МГУ", "http://mgu.ru"), dateFormat.parse("01-01-1995"), dateFormat.parse("01-01-2000"), "", "Курс молодого бойца2");
        organizationsEducation.add(educationOrg1);
        organizationsEducation.add(educationOrg2);
        organizationEducationSection = new OrganizationSection("Образование", organizationsEducation);
        resume.setSections(SectionType.EDUCATION, organizationEducationSection);
        //EXPERIENCE
        jobOrg1 = new Organization(new Link("Завод 1", "http://zavod1.ru"), dateFormat.parse("01-01-2000"), dateFormat.parse("01-01-2001"), "Младший научный сотрудник", "Работаем и учимся работать11111");
        jobOrg2 = new Organization(new Link("Завод 2", "http://zavod2.ru"), dateFormat.parse("01-01-2001"), dateFormat.parse("01-01-2002"), "Научный сотрудник", "Работаем и учимся работать22222");
        organizationsJobEducation.add(jobOrg1);
        organizationsJobEducation.add(jobOrg2);
        organizationJobSection = new OrganizationSection("Опыт работы", organizationsJobEducation);
        resume.setSections(SectionType.EXPERIENCE, organizationJobSection);
        //ACHIEVEMENT          SectionType.ACHIEVEMENT
        achievement.add("Достижения 1111 1 11 1 1 ");
        achievement.add("Достижения 222222222222222 23 32322");
        listAchiSection = new ListSection("Достижения", achievement);
        resume.setSections(SectionType.ACHIEVEMENT, listAchiSection);
        //QUALIFICATIONS
        qualification.add("язык программирования 1");
        qualification.add("язык программирования 2");
        listQualificationSection = new ListSection("Квалификация", qualification);
        resume.setSections(SectionType.QUALIFICATIONS, listQualificationSection);
        //OBJECTIVE
        textObjectiveSection = new TextSection("Позиция", "Описание позиция");
        resume.setSections(SectionType.OBJECTIVE, textObjectiveSection);
        //PERSONAL
        textPersonalSection = new TextSection("Личные качества", "Описание личные качества");
        resume.setSections(SectionType.PERSONAL, textPersonalSection);



        //Contacts data
        resume.setContactData(ContactType.TELEFON, "+79888887946");
        resume.setContactData(ContactType.EMAIL, "email@ya.ru");
        ContentResumeHTML contentResumeHTML = new ContentResumeHTML(resume);
        OutHTMLResume outHTMLResume = new OutHTMLResume(contentResumeHTML);
        outHTMLResume.show();
    }
}
