package com.igorole.basejava.webapp.storage.serializer;

import com.igorole.basejava.webapp.model.*;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamIO {

    private void parse(String sectionType) {
        SectionType st = SectionType.valueOf(sectionType);
        switch (st) {
            case PERSONAL:
                break;
            case EDUCATION:
                break;
        }

    }

    private void writeTextSection(DataOutputStream dos, SectionType type, Map.Entry<SectionType, Section> section) throws IOException {
        dos.writeUTF(type.name());
        dos.writeUTF(section.getValue().getClass().getSimpleName());
        dos.writeUTF(((TextSection) section.getValue()).getContent());
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

            for (SectionType type : SectionType.values()) {
                Map<SectionType, Section> sections = r.getSections(type);
                for (Map.Entry<SectionType, Section> section : sections.entrySet()) {
                    if (section.getValue() instanceof TextSection) {
                        writeTextSection(dos, type, section);
                    } else if (section.getValue() instanceof ListSection) {
                        //writeTextSection(dos, type, section);
                    }
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
            while (dis.) {
                String sectionType = dis.readUTF();
                System.out.println(sectionType);
            }
            return resume;
        }
    }


}
