package com.igorole.basejava.webapp;

import com.igorole.basejava.webapp.storage.FileStorage;

import java.io.File;
import java.text.ParseException;

public class TestFileStorage {
    public static void main(String[] args) throws ParseException {
        FileStorage fileStorage = new FileStorage(new File("D:/igo/storageDir"));
        fileStorage.clear();
        for (int i = 0; i < 5; i++) {
            //fileStorage.save(new Resume(""+i, "Ivanov" + i));
            fileStorage.save(GenerateResume.genResume("" + i));
        }
        System.out.println(fileStorage.get("0").getFullName());
        System.out.println(fileStorage.size());
        fileStorage.delete("2");
        System.out.println(fileStorage.size());
    }
}
