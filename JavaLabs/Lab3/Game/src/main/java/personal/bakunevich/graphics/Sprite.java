package personal.bakunevich.graphics;

import personal.bakunevich.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private SpriteSheet sheet;
    private float scale;
    private BufferedImage image;

    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
        image = sheet.getSprite(0);
        image = Utils.resize(image, (int) scale * image.getWidth(), (int) scale * image.getHeight());
    }

    public void render(Graphics2D graphics, float x, float y) {

        graphics.drawImage(image, (int) x, (int) y, null);

    }

}
