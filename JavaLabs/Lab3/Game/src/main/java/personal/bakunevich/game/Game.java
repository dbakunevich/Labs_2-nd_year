package personal.bakunevich.game;

import personal.bakunevich.IO.Input;
import personal.bakunevich.display.Display;
import personal.bakunevich.game.entity.Bullet;
import personal.bakunevich.game.entity.EntityType;
import personal.bakunevich.game.entity.Player;
import personal.bakunevich.game.level.Level;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Sounds;
import personal.bakunevich.utils.Time;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game implements Runnable {

    public static final int     WIDHT           = 1024;
    public static final int     HEIGHT          = 1024;
    public static final String  TITLE           = "Dima";
    public static final int     CLEAR_COLOR     = 0xff000000;
    public static final int     NUM_BUFFERS     = 3;

    public static final float   UPDATE_RATE     = 80.0f;
    public static final float   UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final short   IDLE_TIME       = 1;

    public static final String  ATLAS_FILE_NAME = "tanks.png";
    private static final String BACKGROUND_MUSIC = "/fon.wav";

    private boolean                         isRun;
    private Thread                          gameThread;
    private final Graphics2D                graphics;
    private final Input                     input;
    public   static TextureAtlas            atlas;
    private final Player                    player;
    private final Level                     level;
    //private final CollisionObjects collisionObjects;
    private static Map<EntityType, Bullet>  bullets;
    private final Sounds                    backgroundMusic;


    public Game() {
        isRun = false;
        Display.create(WIDHT, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        level = new Level(atlas);
        player = new Player(Level.getPositionPlayer_X(), Level.getPositionPlayer_Y(), 4, 3, atlas);
        //collisionObjects = new CollisionObjects();
        bullets = new HashMap<>();
        backgroundMusic = new Sounds(BACKGROUND_MUSIC);

    }

    public static void addBullet(EntityType entity, Bullet bullet){
        bullets.put(entity, bullet);
    }

    public static boolean checkBullet(EntityType entity){
        return bullets.containsKey(entity);
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
        if (bullets.size() != 0) {
            for (Iterator<Map.Entry<EntityType, Bullet>> iterator = bullets.entrySet().iterator();
                iterator.hasNext();){
                    Map.Entry<EntityType, Bullet> entry = iterator.next();
                    Bullet bullet = entry.getValue();
                    if (bullet.isLife())
                        bullet.update(input, level);
                    else
                        iterator.remove();
            }
        }
    }

    private void render() {
        Display.clear();

        player.render(graphics);
        level.render(graphics);
        level.renderWater(graphics);

        if (bullets.size() != 0)
            bullets.forEach((type, x) -> x.render(graphics));

        level.renderGrass(graphics);

        Display.swapBuffers();
    }

    public void run() {
        backgroundMusic.sound();
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
