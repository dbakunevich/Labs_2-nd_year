package personal.bakunevich.game;

import personal.bakunevich.display.Display;
import personal.bakunevich.utils.Time;

import java.awt.*;

public class Game implements Runnable {

    public static final int     WIDGHT          = 800;
    public static final int     HEIGHT          = 800;
    public static final String  TITLE           = "Dima";
    public static final int     CLEAR_COLOR     = 0xff0fffaf;
    public static final int     NUM_BUFFERS     = 3;

    public static final float   UPDATE_RATE     = 120.0f;
    public static final float   UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final short   IDLE_TIME       = 1;

    private boolean             isRun;
    private Thread              gameThread;
    private final Graphics2D    graphics;

    //tmp

    float x = 350;
    float y = 350;
    float d = 0;
    float r = 50;

    //end tmp

    public Game() {
        isRun = false;
        Display.create(WIDGHT, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
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
        d += 0.01f;
    }

    private void render() {
        Display.clear();

        graphics.setColor(Color.pink);
        graphics.fillOval((int) ((int) x + Math.sin(d) * 200), (int) y, (int) r * 2, (int) r * 2);

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
