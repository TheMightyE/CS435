import java.util.Arrays;

class HashTable {
    /* An integer array that holds references to locations in array A */
//    int[] table;
    int[] table;
    private int tableSize;
    private int collisions;

    HashTable(int size) {
//        table = new int[size];
        table = new int[size];
        tableSize = size;
        Arrays.fill(table, -1);
    }

    private void setValue(int idx, int val) {
        table[idx] = val;
    }

    int getSize() {
        return tableSize;
    }

    int getCollisions() {
        return collisions;
    }

    /**
     * Computes and returns hash of a given string
     */
    int hashCode(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++)
            h += (int) s.charAt(i);

        return h % table.length;
    }

    int getValue(int idx) {
        return table[idx];
    }

    boolean hashFull() {
        int c = 0;
        for (int i = 0; i < table.length; i++) {
            if (getValue(i) == -1) {
                c++;
            }
        }
        return c <= table.length / 3;
    }

    boolean hashEmpty() {
        for (int i = 0; i < table.length; i++) {
            if (getValue(i) != -1) return false;
        }
        return true;
    }

    void resizeTable(String A) {
        /* This function doubles the size of the table */
        int oldSize = table.length;
        int newSize = table.length * 2;

        int[] oldTable = table;

        /* Make a new table with the new size */
        table = new int[newSize];

        /* Fill new indices with -1 */
        for (int i = 0; i < newSize; i++) {
            setValue(i, -1);
        }
        for (int i = 0; i < oldSize; i++) {
            String w = getValueFromA(oldTable[i], A);
            if (!w.equals(""))
                insertInTable(w, A, oldTable[i]);
        }

    }

    void insertInTable(String w, String A, int freeIdxInA) {
//        table[idx] = referenceToA;
        int idx = hashCode(w);
        int probingIdx = 1;

        /* Check if table is full and increase it's size. */
        if (hashFull()) {
            // TODO: Maybe problem with resizeTable()
            resizeTable(A);
        }

        /* Search for an empty slot in T */
        while (getValue(idx) != -1 && getValue(idx) != -2) {
//            System.out.println("Collision at slot " + idx);
            // TODO: Problem with formula
            /* Compute h(k,i) = (h'(k)+i^2) */
//            idx = (idx + (int) Math.pow(probingIdx, 2)) % table.length;
            idx = (idx + (probingIdx * probingIdx)) % table.length;
            probingIdx++;
            collisions++;
        }

        /* Store the reference to the starting position of the w in A */
        table[idx] = freeIdxInA;

    }

    /**
     * Deletes the value in table T. Does not delete the string in A as per assignment.
     */
    int delete(String s, String A) {
        int idx = search(s, A);
        if (idx != -1) {
            setValue(idx, -2);
            return idx;
        } else {
            return -1;
        }
    }

    /**
     * Searches for a given string in A. If string not found, returns -1.
     */
    int search(String w, String A) {
        int idx = hashCode(w);
        int key = getValue(idx);

        int probingIdx = 1;
        // FIXME: && probingIndex < tableSize?
        while (key == -2 || (key != -1 && !getValueFromA(key, A).equals(w))) {
            idx = (idx + (probingIdx * probingIdx)) % table.length;
            key = getValue(idx);
            probingIdx++;
        }

        if (getValueFromA(key, A).equals(w)) {
            return idx;
        } else {
            return -1;
        }
    }


    String getValueFromA(int i, String A) {
        if (i != -1 && i != -2) {
            String w = "";
            for (int j = i; A.charAt(j) != '\0'; j++) {
                w += A.charAt(j);
            }
            return w;
        } else {
            return "";
        }
    }

}
