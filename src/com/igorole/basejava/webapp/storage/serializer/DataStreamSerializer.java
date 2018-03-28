package com.igorole.basejava.webapp.storage.serializer;

import com.igorole.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamIO {


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
                SectionType type = section.getKey();
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        writeTextSection(dos, section);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeListSection(dos, section);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrganizationSection(dos, section);
                        break;
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
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        readTextSection(dis, type, resume);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        readListSection(dis, type, resume);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        readOrganizationSection(dis, type, resume);
                        break;
                }
            }
            return resume;
        }
    }

    private void writeHead(DataOutputStream dos, Map.Entry<SectionType, Section> section, int len) throws IOException {
        dos.writeUTF(section.getKey().name());
        dos.writeInt(len);
    }

    private void writeTextSection(DataOutputStream dos, Map.Entry<SectionType, Section> section) throws IOException {
        dos.writeUTF(section.getKey().name());
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
                dos.writeUTF(activity.getTitle());
                dos.writeUTF(activity.getDescription());
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

    private void readListSection(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        int size = dis.readInt();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
        resume.addSections(sectionType, new ListSection(items));
    }

    private void readTextSection(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        resume.addSections(sectionType, new TextSection(dis.readUTF()));
    }

}
