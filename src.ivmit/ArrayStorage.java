/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = size();
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r != null && r.uuid.equals(uuid)) {
                return r;
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        int mark = 0;
        int size = size();
        if (size == 0 && storage[0] == null || storage[0].uuid == null) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                mark = i;
            }
        }
        for (int i = mark; i < size; i++) {
            int tmp = i;
            storage[i] = storage[i + 1];
            storage[i + 1] = storage[tmp];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size()];
        int i = 0;
        int size = size();
        for (Resume r : storage) {
            if (r != null && i != size) {
                allResume[i] = r;
                i++;
            }
        }
        return allResume;
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {             //Проверка на null идет последовательно т.к нельзя добавлять по индексу,
                size++;                           // и при удалений все элементы смещаются в лево,
            } else {                               // подсчет закончится при нахождений первого нулевого элемента
                break;
            }
        }
        return size;
    }
}
