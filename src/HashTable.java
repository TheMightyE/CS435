import java.util.Arrays;

/*
 * Implementation of a Hash Table. Global variable 'table' is where the hashed values are stored.
 * Instead of the table pointing to strings, it points to an integer which corresponds to an
 * index in 'String A' in 'Lexicon.java'. The integer value in 'table' is the starting index
 * of the word in 'A'.
 */
class HashTable {
    /* An integer array that holds references to locations in array A */
    int[] table;
    private int collisions;

    HashTable(int size) {
        table = new int[size];
        Arrays.fill(table, -1);
    }

    private void setValue(int idx, int val) {
        table[idx] = val;
    }

    int getSize() {
        return table.length;
    }

    int getCollisions() {
        return collisions;
    }

    int getValue(int idx) {
        return table[idx];
    }

    /**
     * Computes and returns hash of a given string
     */
    private int hashCode(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++)
            h += (int) s.charAt(i);

        return h % table.length;
    }

    private boolean hashFull() {
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

    /**
     * Doubles the size of the table.
     */
    private void resizeTable(String A) {
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

    /**
     * Inserts a reference to the value in A in the table.
     */
    void insertInTable(String w, String A, int freeIdxInA) {
        int idx = hashCode(w);
        int probingIdx = 1;

        /* Check if table is full and increase it's size. */
        if (hashFull()) {
            resizeTable(A);
        }

        /* Search for an empty slot in T */
        while (getValue(idx) != -1 && getValue(idx) != -2) {
            /* Compute h(k,i) = (h'(k)+i^2) */
            idx = (idx + (probingIdx * probingIdx)) % table.length;
            probingIdx++;
            collisions++;
        }

        /* Store the reference to the starting position of the w in A */
        table[idx] = freeIdxInA;

    }

    /**
     * Deletes the value in table T. Does not delete the string in A as per assignment.
     * Deleted slot is set to -2. This simply represents a deleted slot for the search()
     * function.
     */
    int delete(int idx) {
        /* Set slot to -2 to represent deleted slot. */
        setValue(idx, -2);
        return idx;
    }

    /**
     * Searches for a given string in table and in A. If string not found, returns -1.
     */
    int search(String w, String A) {
        int idx = hashCode(w);
        int key = getValue(idx);

        int probingIdx = 1;
        while (key == -2 || key != -1 && !(getValueFromA(key, A)).equals(w)) {
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


    /**
     * Takes the reference value from 'table' and gets the word from 'A'.
     */
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
