package personal.bakunevich;

import java.io.*;
import java.util.ArrayList;

public class WordReader {
    private final Reader reader;
    public WordReader(String nameOfFile) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(nameOfFile)));
        //((BufferedReader) reader).readLine();
    }

    public void close() throws IOException {
        reader.close();
    }

    private boolean isLetterNonSymbol(int curentChar){
        if (curentChar >= 48 && curentChar <= 57)
            return true;
        else if (curentChar >= 65 && curentChar <= 90)
            return true;
        else return curentChar >= 97 && curentChar <= 122;
    }

    public ArrayList<String> readAllWordsFromFile() throws IOException {
        ArrayList <String> arrayOfStrings = new ArrayList<>();

        var stringBuilder = new StringBuilder();
        int curentChar = reader.read();

        while (curentChar != -1) {
            if (isLetterNonSymbol(curentChar)){
                stringBuilder.appendCodePoint(curentChar);
            }
            else{
                if (!stringBuilder.toString().equals("")){
                    arrayOfStrings.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }
            curentChar = reader.read();
        }
        return arrayOfStrings;
    }
}