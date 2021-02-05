package personal.bakunevich;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Calculator {
    public static void main(String @NotNull [] args){

        String nameOfInFile = "/home/dmitry/JavaProjects/ooop-19208/JavaLabs/Lab2/textIn.txt";
        if (args.length == 1 || args.length == 2){
            nameOfInFile = args[0];
        }

//        System.out.println(nameOfInFile);
//        if (args.length == 2){
//            nameOfInFile = args[1];
//        }
//        String nameOfOutFile = "/home/dmitry/JavaProjects/ooop-19208/JavaLabs/Lab1/textOut.txt";
        ReadFile readFile = null;

        try {
            readFile = new ReadFile(nameOfInFile);
            readFile.readCommand();
        }
        catch (IOException e){
            System.err.println("Error: " + e.getLocalizedMessage());
        }
        finally {
            if (null != readFile)
            {
                try
                {
                    readFile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
//            if (null != writer)
//            {
//                try
//                {
//                    writer.close();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace(System.err);
//                }
//            }
        }
    }
}
