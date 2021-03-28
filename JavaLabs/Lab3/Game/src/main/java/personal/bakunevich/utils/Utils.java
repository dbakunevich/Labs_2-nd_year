package personal.bakunevich.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Utils {

    public static BufferedImage resize(BufferedImage image, int weight, int height) {

        BufferedImage newImage = new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, weight, height, null);

        return newImage;

    }

    public static Integer[][] levelParser(String filePath){

        Integer [][] result = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            ArrayList<Integer[]> lvlLines = new ArrayList<>();
            while ((line = reader.readLine()) != null){
                lvlLines.add(str2int_arrays(line.split(" ")));
            }

            result = new Integer[lvlLines.size()][lvlLines.get(0).length];
            for (int i = 0; i < lvlLines.size(); i++){
                result[i] = lvlLines.get(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static Integer[] str2int_arrays(String[] stringArray) {
        Integer [] result = new Integer[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            result[i] = Integer.parseInt(stringArray[i]);
        }

        return result;
    }

}
