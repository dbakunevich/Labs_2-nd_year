package personal.bakunevich.graphics;

import personal.bakunevich.utils.ResourcesLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String imageName) {
        image = ResourcesLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int wight, int height) {
        return image.getSubimage(x, y, wight, height);
    }

}
