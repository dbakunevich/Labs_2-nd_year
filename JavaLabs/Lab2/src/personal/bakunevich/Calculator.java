package personal.bakunevich;

import personal.bakunevich.exeptions.MyExceptions;

import java.io.IOException;

public class Calculator {
    public static void main(String [] args) {
        //textIn.txt
        String nameOfInFile = null;
        System.out.println();
        String nameOfOutFile = null;
        if (args.length == 1){
            nameOfInFile = args[0];
        }
        else if (args.length == 2){
            nameOfInFile = args[0];
            nameOfOutFile = args[1];
        }

        try (CalculateExecutor mainWork = new CalculateExecutor(nameOfInFile, nameOfOutFile)){
            mainWork.run();
        }
        catch (IOException | MyExceptions e){
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }
}
