package personal.bakunevich.game.level;

import personal.bakunevich.utils.Utils;

import java.awt.*;
import java.awt.image.*;

public class Tile {

    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image, int scale, TileType type) {
        this.type = type;
        this.image = Utils.resize(image, image.getWidth() * scale, image.getHeight() * scale);
    }

    protected void render(Graphics2D graphics, int x, int y) {
        image = makeBlackBGToTransparent(image);
        graphics.drawImage(image, x, y, null);
    }

    protected TileType type() {
        return type;
    }

    public BufferedImage makeBlackBGToTransparent(final BufferedImage im) {
        final ImageFilter filter = new RGBImageFilter() {
            public final int markerRGB = 0xFF000000;

            public final int filterRGB(final int x, final int y, final int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return toBufferedImage(Toolkit.getDefaultToolkit().createImage(ip));
    }
    public BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bImage;
    }

}
