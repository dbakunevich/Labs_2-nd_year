package personal.bakunevich.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourcesLoader {

    public static final String PATH = "/";

    public static BufferedImage loadImage(String fileName){

        BufferedImage image = null;
        System.out.println(PATH + fileName);

        try {
            image = ImageIO.read(ResourcesLoader.class.getResourceAsStream(PATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }
}
