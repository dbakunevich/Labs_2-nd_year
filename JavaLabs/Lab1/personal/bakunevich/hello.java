package personal.bakunevich;

import java.io.IOException;
import java.util.ArrayList;


public class hello {
    public static void main(String[] args) {
        String nameOfInFile = "/home/dmitry/Desktop/test/test.txt";
        if (args.length == 1 || args.length == 2){
            nameOfInFile = args[0];
        }
        System.out.println(nameOfInFile);
        if (args.length == 2){
            nameOfInFile = args[1];
        }
        String nameOfOutFile = "/home/dmitry/Desktop/test/test.csv";

        WordReader reader = null;
        WordWriter writer = null;


        try {
            reader = new WordReader(nameOfInFile);
            ArrayList<String> arrayStringsInFile = reader.readAllWordsFromFile();
            CounterWord dictionaryStringsAndCounters = new CounterWord();
            for (var word: arrayStringsInFile) {
                dictionaryStringsAndCounters.countWord(word);
            }
            writer = new WordWriter(nameOfOutFile, dictionaryStringsAndCounters);
            writer.write();

        }
        catch (IOException e) {
            System.err.println("Error :" + e.getLocalizedMessage());
        }
        finally {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }

            if (null != writer)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
