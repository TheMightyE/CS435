import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Lexicon L = new Lexicon();
        L.HashBatch("test.txt");

/*
        L.HashCreate(11);

        L.HashInsert("yol");
        L.HashInsert("yo");
        L.HashInsert("y");
        L.HashInsert("ol");
        L.HashInsert("olo");
        L.HashInsert("l");
        L.HashInsert("lo");
        L.HashInsert("ooo");
        L.HashInsert("catp");
        L.HashInsert("cat");
        L.HashInsert("ca");

        System.out.println("Found yol: " + L.HashSearch("yol"));

        L.HashPrint();

        System.out.print("Number or collisions: ");
        System.out.println(L.getTable().getCollisions());
*/

    }

    private static int hashCode(String s) {
        int len = 11;
        int h = 0;
        for (int i = 0; i < s.length(); i++)
            h += (int) s.charAt(i);

        return h % len;
    }

    public static String getValue(int i) {
        String s = "is\0this\0valid\0?\0";
        String[] a = s.split("\0");
        return a[i];
    }


}
