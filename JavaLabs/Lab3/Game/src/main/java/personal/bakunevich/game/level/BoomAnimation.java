package personal.bakunevich.game.level;

import personal.bakunevich.graphics.TextureAtlas;

import javax.swing.*;
import java.awt.*;

public class BoomAnimation {
    private Tile emptyFrame;
    private Tile firstFrame;
    private Tile secondFrame;
    private Tile thirdFrame;
    private Tile currentTile;
    private static boolean isBoom;
    private static int xBoom = -100;
    private static int yBoom = -100;
    private Timer timer;
    private static long         lastShoutTime;

    public BoomAnimation(TextureAtlas atlas) {
        TextureAtlas atlasBoom = new TextureAtlas("boom1.png");
        emptyFrame = new Tile(atlas.cut(18 * Level.TILE_SCALE, 3 * Level.TILE_SCALE, Level.TILE_SCALE, Level.TILE_SCALE),
                Level.TILE_IN_GAME_SCALE, TileType.EMPTY);
        firstFrame = new Tile(atlasBoom.cut(0, 0,
                Level.TILE_SCALE, Level.TILE_SCALE),
                Level.TILE_IN_GAME_SCALE, TileType.BOOM_1);

        secondFrame = new Tile(atlasBoom.cut(Level.TILE_SCALE, 0,
                Level.TILE_SCALE, Level.TILE_SCALE),
                Level.TILE_IN_GAME_SCALE, TileType.BOOM_2);

        thirdFrame = new Tile(atlasBoom.cut(2 * Level.TILE_SCALE, 0,
                Level.TILE_SCALE, Level.TILE_SCALE),
                Level.TILE_IN_GAME_SCALE, TileType.BOOM_3);
        currentTile = emptyFrame;
        isBoom = false;

        timer = new Timer(100, e -> {
            if (currentTile == emptyFrame){
                currentTile = firstFrame;
            }
            else if (currentTile == firstFrame){
                currentTile = secondFrame;
            }
            else if (currentTile == secondFrame){
                currentTile = thirdFrame;
            }
            else if (currentTile == thirdFrame){
                currentTile = emptyFrame;
                isBoom = false;
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    public static void startBoom(int x, int y){
        xBoom = x - 32;
        yBoom = y - 32;
        isBoom = true;
    }

    public void renderBoom(Graphics2D graphics) {
        if (isBoom) {
            currentTile.render(graphics, xBoom, yBoom);
        }

        //timer.stop();
//            new Thread(() -> {
//                lastShoutTime = System.currentTimeMillis();
//                while (System.currentTimeMillis() - lastShoutTime <= 2500)
//                    firstFrame.render(graphics, xBoom, yBoom);
//            });
//            firstFrame.render(graphics, xBoom, yBoom);
////            while (System.currentTimeMillis() - lastShoutTime <= 2500)
////                firstFrame.render(graphics, xBoom, yBoom);

    }
}
