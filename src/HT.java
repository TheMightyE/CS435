//public class HT implements HashTableInterface {
//    private int collisions;
//    String[] table;
//
//    /**
//     * Computes and returns hash of a given string
//     */
//    @Override
//    public int hashCode(Lexicon L, String s) {
//        int h = 0;
//        for (int i = 0; i < s.length(); i++)
//            h += (int) s.charAt(i);
//
//        return h % L.getTable().length;
//    }
//
//    @Override
//    public int getSize(Lexicon L) {
//        return L.getTableSize();
//    }
//
//    @Override
//    public boolean hashEmpty(Lexicon L) {
//        return false;
//    }
//
//    @Override
//    public boolean hashFull(Lexicon L) {
//        for (int i = 0; i < table.length; i++) {
//            if (getValue(L, i) == null) return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String getValue(Lexicon L, int index) {
//        return L.getTable()[index];
//    }
//
//    @Override
//    public HT hashCreate(Lexicon L, int m) {
//        HT t = new HT();
//        t.table = new String[m];
//
//        return t;
//    }
//
//    @Override
//    public void hashPrint(Lexicon L) {
//        for (int i = 0; i < L.getTableSize(); i++) {
//            System.out.print(L.getTable()[i]);
//            if ((i + 1) != L.getTable().length) {
//                System.out.print(", ");
//            }
//        }
//        System.out.println();
//    }
//
//    @Override
//    public void hashInsert(Lexicon L, String w) {
//
//    }
//
//    @Override
//    public void hashDelete(Lexicon L, String w) {
//
//    }
//
//    @Override
//    public void hashSearch(Lexicon L, String w) {
//
//    }
//
//    @Override
//    public void hashBatch(Lexicon L, String filename) {
//
//    }
//
//    @Override
//    public void resizeTable(Lexicon L) {
//
//    }
//
//    public int getCollisions(Lexicon L) {
//        return L.getTable().getCollisons();
//    }
//
//    public HT getTable(Lexicon L) {
//        return L.T;
//    }
//}
