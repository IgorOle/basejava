package com.igorole.basejava.webapp;

import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.AbstractArrayStorage;
import com.igorole.basejava.webapp.storage.ArrayStorage;


public class MainTestArrayStorage {
    static final AbstractArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r0 = new Resume("uuid0");
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");

        System.out.println("--------------------");
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        System.out.println(ARRAY_STORAGE.size());
        //printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println(ARRAY_STORAGE.size());
        ARRAY_STORAGE.delete(r2.getUuid());
        System.out.println(ARRAY_STORAGE.size());
//        printAll();
//        ARRAY_STORAGE.delete(r1.getUuid());
//        printAll();
//        ARRAY_STORAGE.save(r5);
//        printAll();
//        ARRAY_STORAGE.save(r0);
//        printAll();
//        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//        printAll();
//        ARRAY_STORAGE.delete(r2.getUuid());
//        printAll();
//        ARRAY_STORAGE.clear();
//        printAll();
//        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

//    static void printAll() {
//        System.out.println("\nGet All");
//        for (Resume r : ARRAY_STORAGE.getAll()) {
//            System.out.println("all = " + r);
//        }
//    }
}
