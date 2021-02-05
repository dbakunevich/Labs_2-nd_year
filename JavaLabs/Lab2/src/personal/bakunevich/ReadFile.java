package personal.bakunevich;

import java.io.*;

public class ReadFile {
    private final BufferedReader reader;

    public ReadFile(String nameOfFile) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(nameOfFile));
    }

//    public ReadFile() throws FileNotFoundException {
//        reader = new BufferedReader(new Scanner(System.in));
//    }

    public void readCommand() throws IOException {
        String[] nowLine = reader.readLine().split(" ");
        while (nowLine != null) {
            switch (nowLine[0]) {
                case "#":
                    break;
                case "POP":
                case "PUSH":
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "SORT":
                    break;
                case "PRINT":
                    break;
                case "DEFINE":
                    break;
                default:
                    break;
            }
            nowLine = reader.readLine().split(" ");
        }
    }

    public void close() throws IOException {
        reader.close();
    }

}
