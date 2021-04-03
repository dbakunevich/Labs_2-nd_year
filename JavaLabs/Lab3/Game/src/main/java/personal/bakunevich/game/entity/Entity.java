package personal.bakunevich.game.entity;

import personal.bakunevich.IO.Input;
import personal.bakunevich.game.level.Level;

import java.awt.*;

public abstract class Entity {

    public final EntityType type;

    protected float         x;
    protected float         y;

    public Entity(EntityType type, float x, float y) {
        System.out.printf("POSITION X = %f, POSITION Y = %f", x, y);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public abstract void update(Input input, Level level);

    public abstract void render(Graphics2D graphics);

}
