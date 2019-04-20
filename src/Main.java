import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Lexicon L = new Lexicon();
        if (args.length == 0) {
            System.err.println("Please provide a filename.");
            System.exit(1);
        } else {
            L.HashBatch(args[0]);
        }
    }
}