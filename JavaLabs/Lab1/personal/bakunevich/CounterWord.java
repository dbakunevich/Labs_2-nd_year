package personal.bakunevich;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CounterWord {
    private int counter = 0;
    private final Map <String, Word> dict;

    public CounterWord() {
        dict = new HashMap<>();
    }

    public void countWord(String word) {
        Word assist = dict.get(word);
        if (assist != null){
            assist.increment();
        }
        else{
            dict.put(word, new Word(word, 1));
        }

        counter++;
    }

    /**
     *
     * @return collection of words which have been counted
     */
    public Collection<Word> getWords(){
        return dict.values();
    }

    public double percentOfWord(Word word){
        return ((double) word.getCountOfWord() / counter) * 100;
    }
}
