package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    private int findNumElement(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i] != null && storage[i].uuid.equals(uuid))  {
                return i;
            }
        }
        return -1;
    }

    private int findNumElementEx(String uuid) {
        int pos = findNumElement(uuid);
        if(pos == -1) System.out.println("ERROR: " + uuid + " resume not found");
        return pos;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if(size == storage.length) {
            System.out.println("ERROR: storage is full");
            return;
        }
        if(findNumElement(r.uuid) == -1) {
            storage[size++] = r;
        }
        else {
            System.out.println("ERROR: resume exist");
        }
    }

    public Resume get(String uuid) {
        int pos = findNumElementEx(uuid);
        return pos >= 0 ? storage[pos] : null;
    }

    public boolean update(Resume r) {
        int pos = findNumElementEx(r.uuid);
        if( pos >= 0) {
            storage[pos] = r;
            return true;
        }
        return false;
    }

    public void delete(String uuid) {
        int pos = findNumElementEx(uuid);
        if(pos >= 0) {
            if(size > 1) {
                // переносим крайний элемент на освободившее место (избавление от дырок)
                storage[pos] = storage[size -1];
            }
            storage[size -1] = null;
            size--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
