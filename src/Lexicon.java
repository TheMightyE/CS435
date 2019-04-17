import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Lexicon {
    private HashTable T;
    private String A; // An array of chars, NULL separated
    private int m; // Size of T. Size of A is 15m initially

    /* Possible commands for HashBatch */


    public int getTableSize() {
        return m;
    }

    public HashTable getTable() {
        return T;
    }

    public String getA() {
        return A;
    }

    public void HashCreate(int m) {
        this.m = m;
        int sizeOfA = 15 * m;
        T = new HashTable(m);

        char[] chars = new char[sizeOfA];
        Arrays.fill(chars, ' ');
        /* String that contains just spaces by default. A space character represents a free spot. */
        A = new String(chars);
    }

    public void HashPrint() {
        /* Print index of T and its value from A */
        System.out.print("[HASHTABLE T]\t\t\t\t\t[A]: ");

        /* Print A nicely. */
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (c == '\0') {
                System.out.print("\\");
            } else
                System.out.print(c);
        }
        System.out.println();

        for (int i = 0; i < T.table.length; i++) {
            int key = T.getValue(i);
            String value = T.getValueFromA(key, A);

            System.out.println("[" + i + "] => " + value);
        }
    }

    public void HashInsert(String w) {
        w += '\0';
        char[] c = A.toCharArray();
        /* Find empty spot in A and insert w in it. */
        int freeSpot = findFreeSpaceInA(w);
//        String s = A.substring(0, freeSpot) + w + A.substring(freeSpot + 1);
        for (int i = freeSpot, j = 0; j < w.length(); i++, j++) {
            c[i] = w.charAt(j);
        }
        A = new String(c);

        T.insertInTable(w, A, freeSpot);
        m = T.getSize();
    }

    public int HashDelete(String w) {
        return T.delete(w, A);
    }

    public int HashSearch(String w) {
        return T.search(w, A);
    }

    public void HashBatch(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(" ");
                String argument = "";
                int command = 0;
                if (lineArray.length > 0) {
                    command = Integer.parseInt(lineArray[0]);
                    if (lineArray.length > 1) {
                        argument = lineArray[1];
                    }
                }

                hashBatchHelper(command, argument);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    private void hashBatchHelper(int command, String arg) {
        final int
                INSERT = 10,
                DELETE = 11,
                SEARCH = 12,
                PRINT = 13,
                CREATE = 14,
                COMMENT = 15;

        int idx;
//        System.out.println("command = [" + command + "], arg = [" + arg + "]");
        switch (command) {
            case INSERT:
                HashInsert(arg);
                break;
            case DELETE:
                idx = HashDelete(arg);
                if (idx == -1) {
                    System.out.format("%s not deleted because not found\n", arg);
                } else {
                    System.out.format("%s deleted from slot %d\n", arg, idx);
                }
                break;
            case SEARCH:

                idx = HashSearch(arg);
                if (idx == -1) {
                    System.out.format("%s not found\n", arg);
                } else {
                    System.out.format("%s found at slot %d\n", arg, idx);
                }
                break;
            case PRINT:
                HashPrint();
                break;
            case CREATE:
                HashCreate(Integer.parseInt(arg));
                break;
            case COMMENT:
                break;
            default:
                break;
        }
    }

    public void resizeA(String w) {
        char[] a = A.toCharArray();
        a = Arrays.copyOf(a, (A.length() * 2) + w.length());
        A = new String(a);
    }

    /**
     * Find a spot in A where the w can fit. If it can't fit, new array with double the
     * current space is made.
     */
    private int findFreeSpaceInA(String w) {
        int idx = 0;
        boolean foundSpaceChar = false;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == ' ') {
                foundSpaceChar = true;
                idx = i;
                if (A.length() - i < w.length()) {
                    resizeA(w);
                }
                break;
            }
        }
        if (!foundSpaceChar) {
            idx = A.length();
            resizeA(w);
        }
        return idx;
    }
}
