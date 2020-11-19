/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = size();

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int mark = 0;
        if (size == 0 || uuid == null) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                mark = i;
            }
        }
        if (storage[mark].uuid.equals(uuid)) {
            storage[mark] = null;
            for (int i = mark; i < size; i++) {
                int tmp;
                tmp = i;
                storage[i] = storage[i + 1];
                storage[i + 1] = storage[tmp];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        for (int j = 0; j < size; j++) {
            allResume[j] = storage[j];
        }
        return allResume;
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {                  //Проверка на null идет последовательно т.к нельзя добавлять по индексу,
                size++;                            // и при удалений все элементы смещаются в лево,
            } else {                               // подсчет закончится при нахождений первого нулевого элемента
                break;
            }
        }
        return size;
    }
}
