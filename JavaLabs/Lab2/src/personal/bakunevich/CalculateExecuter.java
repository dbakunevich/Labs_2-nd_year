package personal.bakunevich;

import java.io.*;
import java.util.Arrays;

public class CalculateExecuter implements AutoCloseable{
    private final LineNumberReader reader;
    private final IFactory factory = new Factory();
    private final ICommandContext context;

    public CalculateExecuter(String nameOfInFile, String nameOfOutFile) throws IOException {
        PrintWriter writer;

        if(nameOfInFile != null && nameOfOutFile != null) {
            reader = new LineNumberReader(new BufferedReader(new FileReader(nameOfInFile)));
            writer = new PrintWriter(new FileWriter(nameOfOutFile));
        } else if(nameOfInFile != null){
            reader = new LineNumberReader(new BufferedReader(new FileReader(nameOfInFile)));
            writer = new PrintWriter(new OutputStreamWriter(System.out));
        } else{
            reader = new LineNumberReader(new BufferedReader(new InputStreamReader(System.in)));
            writer = new PrintWriter(new OutputStreamWriter(System.out));
        }

        context = new CommandContext(writer);
    }

    public void run() throws IOException {
        String currentString = reader.readLine();
        while (currentString != null && !currentString.equals("")){
            String[] nowLine = currentString.split(" ");
            try {
                factory.getCommand(nowLine[0]).execute(context, Arrays.copyOfRange(nowLine, 1, nowLine.length));
            } catch (MyExceptions myExceptions) {
                System.err.println(myExceptions.getMessage());
            }
            currentString = reader.readLine();
        }
        try {
            context.getWriter().close();
        } catch (MyExceptions myExceptions) {
            myExceptions.printStackTrace();
        }
    }

    public void close() throws IOException {
        reader.close();
    }

}
