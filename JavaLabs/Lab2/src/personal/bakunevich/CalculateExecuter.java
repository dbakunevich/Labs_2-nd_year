package personal.bakunevich;

import java.io.*;
import java.util.Arrays;

public class CalculateExecuter implements AutoCloseable{
    private final LineNumberReader reader;
    private final IFactory factory = new Factory();
    private final ICommandContext context = new CommandContext();

    public CalculateExecuter(String nameOfFile) throws FileNotFoundException {
        reader = new LineNumberReader(new BufferedReader(new FileReader(nameOfFile)));
    }

    public CalculateExecuter() throws FileNotFoundException {
        this(System.in);
    }

    public CalculateExecuter(InputStream inputStream){
        reader = new LineNumberReader( new BufferedReader(new InputStreamReader(inputStream)));
    }

    public void run() throws IOException {
        String currentString = reader.readLine();
        while (currentString != null){
            String[] nowLine = currentString.split(" ");
            try {
                factory.getCommand(nowLine[0]).execute(context, Arrays.copyOfRange(nowLine, 1, nowLine.length));
            }
            catch (Exception e){
                break;
            }
            currentString = reader.readLine();
        }
    }

    public void close() throws IOException {
        reader.close();
    }

}
