package personal.bakunevich.game.level;

import personal.bakunevich.game.Game;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Level {

    public static final int     TILE_SCALE = 16;
    public static final int     TILE_IN_GAME_SCALE = 4;
    public static final int     SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int     TILES_IN_WIDHT = Game.WIDHT / SCALED_TILE_SIZE;
    public static final int     TILES_IN_HEIGHT = Game.HEIGHT / SCALED_TILE_SIZE;

    private final Map<TileType, Tile> tiles;
    private final Integer[][]         tileMap;
    private final ArrayList<Point>    grassCoords;

    public Level(TextureAtlas atlas) {
        tiles = new HashMap<>();
        tiles.put(TileType.BRICK, new Tile(atlas.cut(16 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                    TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(atlas.cut(16 * TILE_SCALE, 1 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.GRASS, new Tile(atlas.cut(17 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.WATER, new Tile(atlas.cut(16 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.ICE, new Tile(atlas.cut(18 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(atlas.cut(18 * TILE_SCALE, 3 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.EMPTY));

        tileMap = Utils.levelParser("src/main/resources/level.txt");
        grassCoords = new ArrayList<>();
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() == TileType.GRASS)
                    grassCoords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
            }
        }


    }

    public void update() {

    }
    public void render(Graphics2D graphics) {
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() != TileType.GRASS && tile.type() != TileType.EMPTY)
                    tile.render(graphics, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);
            }
        }
    }

    public void renderGrass(Graphics2D graphics) {
        for (Point p : grassCoords){
            tiles.get(TileType.GRASS).render(graphics, p.x, p.y);
        }
    }

    private boolean isBadTile(Tile tile) {
        return tile.type().numeric() == TileType.METAL.numeric() ||
               tile.type().numeric() == TileType.WATER.numeric() ||
               tile.type().numeric() == TileType.BRICK.numeric();
    }

    public boolean collisionObjects(float x, float y){

        int newX1 =  (int) (x + 2) / SCALED_TILE_SIZE;
        int newY1 =  (int) (y + 2) / SCALED_TILE_SIZE;
        int newX2 = Math.min(15, (int) (x + SCALED_TILE_SIZE - 2) / SCALED_TILE_SIZE);
        int newY2 = Math.min(15, (int) (y + SCALED_TILE_SIZE - 2) / SCALED_TILE_SIZE);
        Tile tile1 = tiles.get(TileType.fromNumeric(tileMap[newY1][newX1]));
        Tile tile2 = tiles.get(TileType.fromNumeric(tileMap[newY1][newX2]));
        Tile tile3 = tiles.get(TileType.fromNumeric(tileMap[newY2][newX1]));
        Tile tile4 = tiles.get(TileType.fromNumeric(tileMap[newY2][newX2]));

        return isBadTile(tile1) || isBadTile(tile2) || isBadTile(tile3) || isBadTile(tile4);
    }


}
