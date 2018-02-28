//package com.igorole.basejava.webapp;
//
//
//import com.igorole.basejava.webapp.model.*;
//import com.igorole.basejava.webapp.view.OutHTMLResume;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public class TestResume {
//
//    public static void main(String[] args) throws ParseException {
//        OrganizationSection organizationEducationSection;
//        OrganizationSection organizationJobSection;
//        ListSection listAchiSection;
//        ListSection listQualificationSection;
//        TextSection textPersonalSection;
//        TextSection textObjectiveSection;
//        Organization educationOrg1;
//        Organization educationOrg2;
//        Organization jobOrg1;
//        Organization jobOrg2;
//        ArrayList<Organization> organizationsEducation = new ArrayList<>();
//        ArrayList<Organization> organizationsJobEducation = new ArrayList<>();
//        ArrayList<String> achievement = new ArrayList<>();
//        ArrayList<String> qualification = new ArrayList<>();
//        Resume resume = new Resume("1", "Иванов Иван Иванович");
//        //Education
//        educationOrg1 = new Organization(new Link("РГУ", "http://rgu.ru"), LocalDate.of(1990, 01, 01), LocalDate.of(1995, 01, 01), "", "Курс молодого бойца1");
//        educationOrg2 = new Organization(new Link("МГУ", "http://mgu.ru"), LocalDate.of(1995, 01, 01), LocalDate.of(2000, 01, 01), "", "Курс молодого бойца2");
//        organizationsEducation.add(educationOrg1);
//        organizationsEducation.add(educationOrg2);
//        organizationEducationSection = new OrganizationSection("Образование", organizationsEducation);
//        resume.setSections(SectionType.EDUCATION, organizationEducationSection);
//        //EXPERIENCE
//        jobOrg1 = new Organization(new Link("Завод 1", "http://zavod1.ru"), LocalDate.of(2000, 01, 01), LocalDate.of(2001, 01, 01), "Младший научный сотрудник", "Работаем и учимся работать11111");
//        jobOrg2 = new Organization(new Link("Завод 2", "http://zavod2.ru"), LocalDate.of(2001, 01, 01), LocalDate.of(2002, 01, 01), "Научный сотрудник", "Работаем и учимся работать22222");
//        organizationsJobEducation.add(jobOrg1);
//        organizationsJobEducation.add(jobOrg2);
//        organizationJobSection = new OrganizationSection("Опыт работы", organizationsJobEducation);
//        resume.setSections(SectionType.EXPERIENCE, organizationJobSection);
//        //ACHIEVEMENT          SectionType.ACHIEVEMENT
//        achievement.add("Достижения 1111 1 11 1 1 ");
//        achievement.add("Достижения 222222222222222 23 32322");
//        listAchiSection = new ListSection("Достижения", achievement);
//        resume.setSections(SectionType.ACHIEVEMENT, listAchiSection);
//        //QUALIFICATIONS
//        qualification.add("язык программирования 1");
//        qualification.add("язык программирования 2");
//        listQualificationSection = new ListSection("Квалификация", qualification);
//        resume.setSections(SectionType.QUALIFICATIONS, listQualificationSection);
//        //OBJECTIVE
//        textObjectiveSection = new TextSection("Позиция", "Описание позиция");
//        resume.setSections(SectionType.OBJECTIVE, textObjectiveSection);
//        //PERSONAL
//        textPersonalSection = new TextSection("Личные качества", "Описание личные качества");
//        resume.setSections(SectionType.PERSONAL, textPersonalSection);
//
//
//        //Contacts data
//        resume.setContactData(ContactType.TELEFON, "+79888887946");
//        resume.setContactData(ContactType.EMAIL, "email@ya.ru");
//        ContentResumeHTML contentResumeHTML = new ContentResumeHTML(resume);
//        OutHTMLResume outHTMLResume = new OutHTMLResume(contentResumeHTML);
//        outHTMLResume.show();
//    }
//}
