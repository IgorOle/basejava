package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends SortedArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    private int size = 0;
    Resume[] storage = new Resume[STORAGE_LIMIT];



}
