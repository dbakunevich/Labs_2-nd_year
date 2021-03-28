package personal.bakunevich.game.level;

import personal.bakunevich.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image, int scale, TileType type) {
        this.type = type;
        this.image = Utils.resize(image, image.getWidth() * scale, image.getHeight() * scale);

    }

    protected void render(Graphics2D graphics, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    protected TileType type() {
        return type;
    }

}
