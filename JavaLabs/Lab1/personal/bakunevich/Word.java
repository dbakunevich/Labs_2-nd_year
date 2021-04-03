package personal.bakunevich;

import java.lang.Comparable;

public class Word implements Comparable <Word> {
    private final String word;
    private int countOfWord;

    public Word(String word, int countOfWord) {
        this.word = word;
        this.countOfWord = countOfWord;
    }

    public String getWord() {
        return word;
    }

    public int getCountOfWord() {
        return countOfWord;
    }

    public void increment(){
        this.countOfWord++;
    }

    @Override
    public int compareTo(Word o) {
        return Integer.compare(this.getCountOfWord(), o.getCountOfWord());
    }
}
