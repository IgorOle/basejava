package com.igorole.basejava.webapp;


import com.igorole.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class GenerateResume {

    public static Resume genResume(String postfix) {
        String fullName = "Иванов" + postfix + " Иван Иванович";
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
        Resume resume = new Resume(postfix, fullName);
        //Education
        educationOrg1 = new Organization("РГУ", "http://rgu.ru");
        educationOrg1.addActivity(LocalDate.of(1990, 01, 01), LocalDate.of(1995, 01, 01), "", "Курс молодого бойца1");
        educationOrg1.addActivity(LocalDate.of(1995, 01, 01), LocalDate.of(2000, 01, 01), "", "Курс молодого бойца2");
        educationOrg2 = new Organization("МГУ", "http://mgu.ru");
        educationOrg2.addActivity(LocalDate.of(2000, 01, 01), LocalDate.of(2001, 01, 01), "", "Курс молодого бойца2");
        organizationsEducation.add(educationOrg1);
        organizationsEducation.add(educationOrg2);
        organizationEducationSection = new OrganizationSection(organizationsEducation);
        resume.addSections(SectionType.EDUCATION, organizationEducationSection);
        //EXPERIENCE
        jobOrg1 = new Organization("ООО Ростсельмаш", null);
        jobOrg1.addActivity(LocalDate.of(2001, 01, 01), LocalDate.of(2002, 01, 01), "Новичек", "Работаем и учимся работать11111");
        jobOrg1.addActivity(LocalDate.of(2002, 01, 01), LocalDate.of(2003, 01, 01), "Продвинутый новичек", "Продолжаем учиться и работать22222");
        organizationsJobEducation.add(jobOrg1);
        jobOrg2 = new Organization("ПАО НИИ", "http://nii.ru");
        jobOrg1.addActivity(LocalDate.of(2003, 01, 01), LocalDate.of(2008, 01, 01), "Научный сотрудник", "Продолжаем учиться и работать3333");
        organizationsJobEducation.add(jobOrg2);
        organizationJobSection = new OrganizationSection(organizationsJobEducation);
        resume.addSections(SectionType.EXPERIENCE, organizationJobSection);
        //ACHIEVEMENT          SectionType.ACHIEVEMENT
        achievement.add("Достижения 1111 1 11 1 1 ");
        achievement.add("Достижения 222222222222222 23 32322");
        listAchiSection = new ListSection(achievement);
        resume.addSections(SectionType.ACHIEVEMENT, listAchiSection);
        //QUALIFICATIONS
        qualification.add("язык программирования 1");
        qualification.add("язык программирования 2");
        listQualificationSection = new ListSection(qualification);
        resume.addSections(SectionType.QUALIFICATIONS, listQualificationSection);
        //OBJECTIVE
        textObjectiveSection = new TextSection("Описание позиция");
        resume.addSections(SectionType.OBJECTIVE, textObjectiveSection);
        //PERSONAL
        textPersonalSection = new TextSection("Описание личные качества");
        resume.addSections(SectionType.PERSONAL, textPersonalSection);
        //Contacts data
        resume.addContact(ContactType.PHONE, "+79888887946");
        resume.addContact(ContactType.MAIL, "email@ya.ru");
        return resume;
    }
}
