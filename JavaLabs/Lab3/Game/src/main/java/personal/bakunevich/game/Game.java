package personal.bakunevich.game;

import personal.bakunevich.IO.Input;
import personal.bakunevich.display.Display;
import personal.bakunevich.display.Menu;
import personal.bakunevich.game.entity.Bullet;
import personal.bakunevich.game.entity.Enemie;
import personal.bakunevich.game.entity.EntityType;
import personal.bakunevich.game.entity.Player;
import personal.bakunevich.game.level.BoomAnimation;
import personal.bakunevich.game.level.CollisionObjects;
import personal.bakunevich.game.level.Level;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Sounds;
import personal.bakunevich.utils.Time;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game implements Runnable {

    public static final int     WIDHT           = 1024;
    public static final int     HEIGHT          = 1024;
    public static final String  TITLE           = "Dima";
    public static final int     CLEAR_COLOR     = 0xff000000;
    public static final int     NUM_BUFFERS     = 3;

    public static final float   UPDATE_RATE     = 60.0f;
    public static final float   UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final short   IDLE_TIME       = 1;

    public static final String  ATLAS_FILE_NAME = "tanks.png";
    private static final String BACKGROUND_MUSIC = "/fon.wav";

    private boolean                         isRun;
    private boolean                         isStartMenu;
    private boolean                         isFinishMenu;
    private Thread                          gameThread;
    private final Graphics2D                graphics;
    private final Input                     input;
    public static TextureAtlas              atlas;
    private Menu                            menu;
    private final Player                    player;
    private final Level                     level;
    private static Map<EntityType, Bullet>  bullets;
    private static ArrayList<Enemie>        enemies;
    private final Sounds                    backgroundMusic;
    public final CollisionObjects           collisionObjects;
    public BoomAnimation                    boomAnimation;
    public static int                       whichFinish;
    private long                            currentTime;


    public Game() {
        isRun = false;
        isStartMenu = false;
        isFinishMenu = false;
        whichFinish = 0;
        Display.create(WIDHT, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        menu = new Menu(atlas);
        level = new Level(atlas);
        player = new Player(Level.getPositionPlayer_X(), Level.getPositionPlayer_Y(), 4, 3, atlas);
        collisionObjects = new CollisionObjects();
        bullets = new HashMap<>();
        boomAnimation = new BoomAnimation(atlas);
        enemies = new ArrayList<>();
        enemies.add(new Enemie(Level.getPositionEnemies_X(), Level.getPositionEnemies_Y(), 4, 3, atlas));
        backgroundMusic = new Sounds(BACKGROUND_MUSIC, 0.65);
        currentTime = System.currentTimeMillis();

    }

    private void MBadd(){
        if (System.currentTimeMillis() - currentTime >= 5000) {
            currentTime = System.currentTimeMillis();
            enemies.add(new Enemie(Level.getPositionEnemies_X(), Level.getPositionEnemies_Y(), 4, 3, atlas));
        }
    }

    public static void addBullet(EntityType entity, Bullet bullet){
        bullets.put(entity, bullet);
    }

    public static boolean checkBullet(EntityType entity){
        return !bullets.containsKey(entity);
    }


    public synchronized void start() {

        if (isRun) return;

        isRun = true;
        isStartMenu = true;

        gameThread = new Thread(this);
        gameThread.start();
        if (isStartMenu) {
            runMenu();
            isStartMenu = false;
        }
    }

    public synchronized void finish() {
        if (!isRun) return;

        System.out.print("YOU LOSE\n");
        isRun = false;

        //cleanUp();
    }

    private void update() {
        player.update(input, level);
        for (Enemie e: enemies) {
            e.update(input, level);
            level.update(player, e);
        }
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
        for (Enemie e: enemies) {
            e.render(graphics);
        }
        level.render(graphics);
        level.renderWater(graphics);

        if (bullets.size() != 0)
            bullets.forEach((type, x) -> x.render(graphics));

        level.renderGrass(graphics);
        boomAnimation.renderBoom(graphics);

        Display.swapBuffers();
    }

    private void updateMenu() {
        menu.update(input);
    }

    private void renderMenu() {
        Display.clear();

        menu.render(graphics);

        Display.swapBuffers();
    }

    private void runMenu(){
        float delta = 0.0f;
        long startTime = Time.getSecond();
        while (isStartMenu) {
            long nowTime = Time.getSecond();
            long elapsedTime = nowTime - startTime;
            startTime = nowTime;

            boolean isRender = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta >= 1) {
                updateMenu();
                delta--;
                isRender = true;
            }
            if (isRender) {
                renderMenu();
            }
            else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            isStartMenu = false;
        }
    }

    public void run() {
        start();
        backgroundMusic.sound();
        backgroundMusic.setLoop();

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
            if (whichFinish != 0) {
                if (whichFinish == 1)
                    System.out.println("YOU BASE IS BROKEN");
                else if (whichFinish == 9)
                    System.out.println("YOUR TANK IS DIE");
                else if (whichFinish == 2){
                    System.out.println("ALL ENEMIES ARE DIE");
                }
                break;
            }
        }
        backgroundMusic.stop();
        render();
        finish();
    }

    private void cleanUp() {
        Display.destroy();
    }
}
