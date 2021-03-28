package personal.bakunevich.game;

import personal.bakunevich.IO.Input;
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
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 *SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int x, y, weight, height;

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

        heading = Heading.NORTH;
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
                heading = Heading.NORTH;
            } else if (input.getKey(KeyEvent.VK_RIGHT)) {
                newX += 1;
                heading = Heading.EAST;
            } else if (input.getKey(KeyEvent.VK_DOWN)) {
                newY += 1;
                heading = Heading.SOUTH;
            } else if (input.getKey(KeyEvent.VK_LEFT)) {
                newX -= 1;
                heading = Heading.WEST;
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

            if (level.collisionObjects(newX, newY)) {
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
    }
}
