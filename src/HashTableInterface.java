public interface HashTableInterface {
    int hashCode(Lexicon L, String w);

    int getSize(Lexicon L);

    boolean hashEmpty(Lexicon L); // Check if L is empty

    boolean hashFull(Lexicon L); // Check if L can maintain more words

    String getValue(Lexicon L, int index);

    void hashCreate(Lexicon L, int m); // Create T, A. T will have m slots; A should be 15m

    void hashPrint(Lexicon L); // Print of L

    void hashInsert(Lexicon L, String w); //Insert w into L (and T and A)

    void hashDelete(Lexicon L, String w); //Delete w from L (but not necessarily from A)

    void hashSearch(Lexicon L, String w); //Search for string in L (and this means T)

    void hashBatch(Lexicon L, String filename);

    void resizeTable(Lexicon L);


}
