package personal.bakunevich.game.entity;

import personal.bakunevich.IO.Input;
import personal.bakunevich.game.Game;
import personal.bakunevich.game.level.CollisionObjects;
import personal.bakunevich.game.level.Level;
import personal.bakunevich.graphics.Sprite;
import personal.bakunevich.graphics.SpriteSheet;
import personal.bakunevich.graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_COUNT = 1;

    private enum Heading{
        NORTH_1(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST_1(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH_1(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 *SPRITE_SCALE),
        WEST_1(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),

        NORTH_2(1 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST_2(7 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH_2(5 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 *SPRITE_SCALE),
        WEST_2(3 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);


        private final int x, y, weight, height;

        Heading(int x, int y, int weight, int height){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.height = height;
        }

        protected BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, weight, height);
        }
    }

    private Heading                 heading;
    private Map<Heading, Sprite>    spriteMap;
    private float                   scale;
    private float                   speed;

    public Player (float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(EntityType.Player, x, y);

        this.scale = scale;
        this.speed = speed;

        heading = Heading.NORTH_1;
        spriteMap = new HashMap<Heading, Sprite>();

        for (Heading heading : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(heading.texture(atlas), SPRITES_COUNT, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(heading, sprite);
        }
    }
    @Override
    public void update(Input input, Level level) {

        float newX = x;
        float newY = y;

        for (int i = 0; i < speed; i++){
            if (input.getKey(KeyEvent.VK_UP)){
                newY -= 1;
                if ((int) newY % 6 < 3)
                    heading = Heading.NORTH_1;
                else
                    heading = Heading.NORTH_2;
            } else if (input.getKey(KeyEvent.VK_RIGHT)) {
                newX += 1;
                if ((int) newX % 6 < 3)
                    heading = Heading.EAST_1;
                else
                    heading = Heading.EAST_2;
            } else if (input.getKey(KeyEvent.VK_DOWN)) {
                newY += 1;
                if ((int) newY % 6 < 3)
                    heading = Heading.SOUTH_1;
                else
                    heading = Heading.SOUTH_2;
            } else if (input.getKey(KeyEvent.VK_LEFT)) {
                newX -= 1;
                if ((int) newX % 6 < 3)
                    heading = Heading.WEST_1;
                else
                    heading = Heading.WEST_2;
            }
            if (input.getKey(KeyEvent.VK_SPACE)){
                //bullet.update(input, level);
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

            if (CollisionObjects.collisionObjects(newX, newY)) {
                newX = x;
                newY = y;
            }

            x = newX;
            y = newY;
        }
    }
    @Override
    public void render(Graphics2D graphics) {
        spriteMap.get(heading).render(graphics, x, y);
        //spriteMap.get(Bullet).render(graphics, x, y);
    }
}
