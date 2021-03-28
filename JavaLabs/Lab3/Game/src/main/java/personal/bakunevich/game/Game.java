package personal.bakunevich.game;

import personal.bakunevich.IO.Input;
import personal.bakunevich.display.Display;
import personal.bakunevich.game.level.Level;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Time;

import java.awt.*;

public class Game implements Runnable {

    public static final int     WIDHT           = 1024;
    public static final int     HEIGHT          = 1024;
    public static final String  TITLE           = "Dima";
    public static final int     CLEAR_COLOR     = 0xff000000;
    public static final int     NUM_BUFFERS     = 3;

    public static final float   UPDATE_RATE     = 90.0f;
    public static final float   UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final short   IDLE_TIME       = 1;

    public static final String  ATLAS_FILE_NAME = "tanks.png";

    private boolean             isRun;
    private Thread              gameThread;
    private final Graphics2D    graphics;
    private final Input               input;
    private TextureAtlas        atlas;
    private final Player              player;
    private final Level               level;

    //tmp
    float x = WIDHT / 2 - 100.0f;
    float y = HEIGHT / 2 - 100.0f;
    float d = 0;
    float r = 50;

    float speed = 3;
    //end tmp

    public Game() {
        isRun = false;
        Display.create(WIDHT, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        player = new Player(x - 50, y + 330, 4, 3, atlas);
        level = new Level(atlas);

    }

    public synchronized void start() {

        if (isRun) return;

        isRun = true;

        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void finish() {
        if (!isRun) return;

        isRun = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    private void update() {
        player.update(input, level);
        level.update();
    }

    private void render() {
        Display.clear();

        player.render(graphics);
        level.render(graphics);
        level.renderGrass(graphics);

        Display.swapBuffers();
    }

    public void run() {

        int FPS = 0;
        int UPD = 0;
        int UPD_LOOP = 0;

        long count = 0;

        float delta = 0.0f;

        long startTime = Time.getSecond();
        while (isRun) {
            long nowTime = Time.getSecond();
            long elapsedTime = nowTime - startTime;
            startTime = nowTime;

            count += elapsedTime;

            boolean isRender = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta >= 1){
                update();
                UPD++;
                delta--;
                if (isRender) {
                    UPD_LOOP++;
                }
                isRender = true;
            }

            if (isRender) {
                render();
                FPS++;
            }
            else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            if (count >= Time.SECOND){
                Display.setTitle(TITLE + "\t FPS: " + FPS + "\t UPD: " + UPD + "\t UPD_LOOP: " + UPD_LOOP);
                UPD_LOOP = 0;
                UPD = 0;
                FPS = 0;
                count = 0;
            }
        }
    }

    private void cleanUp() {
        Display.destroy();
    }
}
