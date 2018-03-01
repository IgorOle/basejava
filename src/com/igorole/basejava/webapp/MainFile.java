package com.igorole.basejava.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File(".");
        treeDirectory(dir);
    }

    public static void treeDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(file.getName());
                    treeDirectory(file);
                }
            }
        }
    }
}
