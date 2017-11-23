import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static int fullSize = 0;
    Resume[] storage = new Resume[10000];

    private int findNumElement(String uuid) {
        for(int i = 0; i < fullSize; i++) {
            if(storage[i] != null && storage[i].uuid.equals(uuid))  {
                return i;
            }
        }
        return -1;
    }

    void clear() {
        Arrays.fill(storage, null);
        fullSize = 0;
    }

    void save(Resume r) {
        storage[fullSize++] = r;
    }


    Resume get(String uuid) {
        int pos;

        pos = findNumElement(uuid);
        if(pos >= 0) {
            return storage[pos];
        }
        return null;
    }

    void delete(String uuid) {
        int pos;

        pos = findNumElement(uuid);
        if(pos >= 0) {
            // переносим крайний элемент на освободившее место (избавление от дырок)
            storage[pos] = storage[fullSize-1];
            storage[fullSize-1] = null;

            fullSize--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, fullSize);
    }

    int size() {
        return fullSize;
    }
}
