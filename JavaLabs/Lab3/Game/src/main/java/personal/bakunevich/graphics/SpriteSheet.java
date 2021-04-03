package personal.bakunevich.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private final BufferedImage sheet;

    private final int spriteCount;
    private final int scale;
    private final int spritesInWight;

    public SpriteSheet(BufferedImage sheet, int spriteCount, int scale) {

        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.scale = scale;

        this.spritesInWight = sheet.getWidth() / scale;

    }

    public BufferedImage getSprite(int index) {

        index = index % spriteCount;

        int x = index % spritesInWight * scale;
        int y = index / spritesInWight * scale;

        return sheet.getSubimage(x, y, scale, scale);
    }


}
