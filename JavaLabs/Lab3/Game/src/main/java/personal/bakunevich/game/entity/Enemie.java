package personal.bakunevich.game.entity;

import personal.bakunevich.IO.Input;
import personal.bakunevich.game.Game;
import personal.bakunevich.game.level.CollisionObjects;
import personal.bakunevich.game.level.Level;
import personal.bakunevich.graphics.Sprite;
import personal.bakunevich.graphics.SpriteSheet;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Sounds;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Enemie extends Entity{

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_COUNT = 1;
    public static final int newAct = 20;
    private static final String shoutSoundURL = "/shootSound.wav";
    private static final String moveSoundURL = "/moveSound.wav";
    private static long         lastShoutTime;
    private static long         lastMoveTime;
    private static Random       random;
    private static int          counterAct;
    private static int          currentAct;
    public static int           currentIndex;


    private enum Heading{
        NORTH_1(8 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 1),
        EAST_1(14 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 2),
        SOUTH_1(12 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 3),
        WEST_1(10 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 4),

        NORTH_2(9 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 1),
        EAST_2(15 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 2),
        SOUTH_2(13 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 3),
        WEST_2(11 * SPRITE_SCALE, 0, SPRITE_SCALE, SPRITE_SCALE, 4);


        private final int x, y, weight, height, n;

        Heading(int x, int y, int weight, int height, int n){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.height = height;
            this.n = n;
        }

        public int numeric() {
            return n;
        }

        protected BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, weight, height);
        }
    }

    private Enemie.Heading heading;
    private final Map<Heading, Sprite> spriteMap;
    private final float                   scale;
    private final float                   speed;
    private final Sounds moveSound;
    private final Sounds                  shoutSound;



    public Enemie(float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(EntityType.Enemies, x, y);

        this.scale = scale;
        this.speed = speed;

        moveSound = new Sounds(moveSoundURL, 2);
        shoutSound = new Sounds(shoutSoundURL, 2);

        heading = Enemie.Heading.NORTH_1;
        spriteMap = new HashMap<>();
        lastShoutTime = System.currentTimeMillis();
        lastMoveTime = System.currentTimeMillis();


        for (Enemie.Heading heading : Enemie.Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(heading.texture(atlas), SPRITES_COUNT, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(heading, sprite);
        }
        random = new Random();
        currentAct = random.nextInt(5);
        counterAct = 0;

    }
    @Override
    public void update(Input input, Level level) {
        if (counterAct > newAct){
            currentAct = random.nextInt(5);
            counterAct = 0;
        }
        else
            counterAct++;
        float newX = x;
        float newY = y;

        for (int i = 0; i < speed; i++){
            if (currentAct == 0){
                newY -= 1;
                if ((int) newY % 6 < 3)
                    heading = Enemie.Heading.NORTH_1;
                else
                    heading = Enemie.Heading.NORTH_2;
            } else if (currentAct == 1) {
                newX += 1;
                if ((int) newX % 6 < 3)
                    heading = Enemie.Heading.EAST_1;
                else
                    heading = Enemie.Heading.EAST_2;
            } else if (currentAct == 2) {
                newY += 1;
                if ((int) newY % 6 < 3)
                    heading = Enemie.Heading.SOUTH_1;
                else
                    heading = Enemie.Heading.SOUTH_2;
            } else if (currentAct == 3) {
                newX -= 1;
                if ((int) newX % 6 < 3)
                    heading = Enemie.Heading.WEST_1;
                else
                    heading = Enemie.Heading.WEST_2;
            }
            if (currentAct == 4){
                if (Game.checkBullet(EntityType.Enemies) && (System.currentTimeMillis() - lastShoutTime >= 1200)) {
                    Bullet bullet = new Bullet(x + SPRITE_SCALE + 6, y + SPRITE_SCALE + 6, 5, speed * 2, Game.atlas, heading.numeric());
                    Game.addBullet(EntityType.Enemies, bullet);
                    shoutSound.sound();
                    lastShoutTime = System.currentTimeMillis();
                }
            }
            if (newX < 0) {
                newX = 0;
                break;
            } else if (newX >= Game.WIDHT - SPRITE_SCALE * scale) {
                newX = Game.WIDHT - SPRITE_SCALE * scale;
                break;
            }
            if (newY < 0) {
                newY = 0;
                break;
            } else if (newY >= Game.HEIGHT - SPRITE_SCALE * scale) {
                newY = Game.HEIGHT - SPRITE_SCALE * scale;
                break;
            }

            if (CollisionObjects.collisionTanks(newX, newY, Level.getArrayEntityPositions())) {
                newX = x;
                newY = y;
                break;
            }

            x = newX;
            y = newY;
        }
    }
    @Override
    public void render(Graphics2D graphics) {
        spriteMap.get(heading).render(graphics, x, y);
    }

    public static void isDie() {
        Game.whichFinish = 2;
    }

    public int currentPosition_X(){
        return (int) x;
    }
    public int currentPosition_Y(){
        return (int) y;
    }
}
