// Liam Keliher, 2019
//
// This class is used to test Word, WordCountPair, and HashTable.
// The main method prompts the user for the full name of an input
// text file.  It then extracts every word in that file that consists
// entirely of English alphabet letters, and for each one, inserts
// a (key,value) into a hash table, where the key is the word and the
// value is the number of times that the word has been encoutered so far.
//
// Since this code is tailored to a specific plain-text version of
// Tolstoy's "War and Peace", any line beginning with "BOOK" or
// "CHAPTER" is skipped.
//
// After each word is found in the input file, a line is written to
// an output file consisting of that word followed by the number of
// occurrences of that word so far.
//
// The full path name of the output file is the full path name of the
// input file with ".out" appended.


import java.io.*;
import java.util.*;

public class TestHashTable {
    //--------------------------------------------------------------------
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(3);

        Scanner keyboard = new Scanner(System.in);
        String inPath = null;
        File inF = null;
        while (inF == null || !inF.isFile()) {
            System.out.println("Enter the full path name of the input file:");
            inPath = keyboard.nextLine();
            inF = new File(inPath);
        } // while
        keyboard.close();

        try (BufferedReader br = new BufferedReader(new FileReader(inF))) {    // try-with-resources
            String outPath = inPath + ".out.txt";
            File outF = new File(outPath);
            PrintWriter pw = new PrintWriter(outF);
            int lineCount = 0;
            while (br.ready()) {
                lineCount++;
                System.err.println("lineCount = " + lineCount);
                String line = br.readLine().trim();
                if (line.length() > 0 && !line.startsWith("BOOK") && !line.startsWith("CHAPTER")) {
                    String[] tokens = line.split(" +");
                    for (int i = 0; i < tokens.length; i++) {
                        String s = tokens[i];
                        Word w = Word.makeWord(s);
                        if (w != null) {
                            Integer count = hashTable.get(w);
                            if (count == null) {
                                hashTable.put(w, 1);
                                pw.println(w + " 1");
                            } // if
                            else {
                                count++;
                                hashTable.put(w, count);
                                pw.println(w + " " + count);
                            } // else
                        } // if
                    } // for i
                } // if
            } // while
            pw.close();
        } // try
        catch(IOException e) {
            System.out.println("Some kind of I/O exception occurred");
            e.printStackTrace();
        } // catch()
    } // main(String[])
    //--------------------------------------------------------------------
} // class TestHashTable
