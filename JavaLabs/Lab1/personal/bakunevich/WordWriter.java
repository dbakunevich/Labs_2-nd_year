package personal.bakunevich;


import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class WordWriter {
    private final CounterWord counterWord;
    private final Writer writer;

    public WordWriter(String nameOfFile, CounterWord counterWord) throws IOException {
        writer = new BufferedWriter(new FileWriter(nameOfFile));
        this.counterWord = counterWord;
    }

    private Word[] sort(){
        var sorted = counterWord.getWords().toArray(new Word[0]);
        Arrays.sort(sorted, Collections.reverseOrder());
        return sorted;
    }

    public void write() throws IOException {
        Word [] arraySortedWord = sort();
        for (Word word: arraySortedWord) {
            writer.write(String.format(Locale.US,"%s, %d, %.2f%%\n", word.getWord(), word.getCountOfWord(), counterWord.percentOfWord(word)));
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}
