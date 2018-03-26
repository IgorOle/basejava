package com.igorole.basejava.webapp.storage.serializer;

import com.igorole.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamIO {

    private void writeHead(DataOutputStream dos, Map.Entry<SectionType, Section> section, int len) throws IOException {
        dos.writeUTF(section.getKey().name());
        dos.writeUTF(section.getValue().getClass().getSimpleName());
        dos.writeInt(len);
    }

    private void writeTextSection(DataOutputStream dos, Map.Entry<SectionType, Section> section) throws IOException {
        writeHead(dos, section, 1);
        dos.writeUTF(((TextSection) section.getValue()).getContent());
    }

    private void writeListSection(DataOutputStream dos, Map.Entry<SectionType, Section> section) throws IOException {
        List<String> items = ((ListSection) section.getValue()).getItems();
        writeHead(dos, section, items.size());
        for (String text : items) {
            dos.writeUTF(text);
        }
    }

    private void writeOrganizationSection(DataOutputStream dos, Map.Entry<SectionType, Section> section) throws IOException {
        List<Organization> items = ((OrganizationSection) section.getValue()).getOrganizations();
        writeHead(dos, section, items.size());
        for (Organization organization : items) {
            ArrayList<Organization.Activity> activities = organization.getActivities();
            dos.writeUTF(organization.getName());
            dos.writeUTF(organization.getUrl());
            dos.writeInt(activities.size());
            for (Organization.Activity activity : activities) {
                dos.writeUTF(activity.getStartDate().toString());
                dos.writeUTF(activity.getEndDate().toString());
                dos.writeUTF(activity.getDescription());
                dos.writeUTF(activity.getTitle());
            }
        }
    }

    private void readOrganizationSection(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        int countOrganizations = dis.readInt();
        List<Organization> organizations = new ArrayList<>();
        for (int i = 0; i < countOrganizations; i++) {
            Organization organization = new Organization(dis.readUTF(), dis.readUTF());
            int countActivity = dis.readInt();
            for (int j = 0; j < countActivity; j++) {
                organization.addActivity(LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF());
            }
            organizations.add(organization);
        }
        resume.addSections(sectionType, new OrganizationSection(organizations));
    }

    private void readLineSection(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        int size = dis.readInt();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
        resume.addSections(sectionType, new ListSection(items));
    }

    private void readTextSection(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        dis.readInt();
        resume.addSections(sectionType, new TextSection(dis.readUTF()));
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            for (Map.Entry<SectionType, Section> section : sections.entrySet()) {
                if (section.getValue() instanceof TextSection) {
                    writeTextSection(dos, section);
                } else if (section.getValue() instanceof ListSection) {
                    writeListSection(dos, section);
                } else if (section.getValue() instanceof OrganizationSection) {
                    writeOrganizationSection(dos, section);
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            while (dis.available() > 0) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                String classSection = dis.readUTF();
                switch (classSection) {
                    case "TextSection":
                        readTextSection(dis, sectionType, resume);
                        break;
                    case "ListSection":
                        readLineSection(dis, sectionType, resume);
                        break;
                    case "OrganizationSection":
                        readOrganizationSection(dis, sectionType, resume);
                        break;
                }
            }
            return resume;
        }
    }

}
