package personal.bakunevich.game.level;

import personal.bakunevich.game.Game;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Level {

    public static final int     TILE_SCALE = 16;

    public static final int     TILE_IN_GAME_SCALE = 4;
    public static final int     SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int     TILES_IN_WIDHT = Game.WIDHT / SCALED_TILE_SIZE;
    public static final int     TILES_IN_HEIGHT = Game.HEIGHT / SCALED_TILE_SIZE;

    private static Map<TileType, Tile>  tiles;
    private static Integer[][]          tileMap;
    private final ArrayList<Point>      grassCoords;
    private final ArrayList<Point>      waterCoords;
    private TileType                    waterType;
    private Timer                       timer;

    public Level(TextureAtlas atlas) {
        tiles = new HashMap<>();
        tiles.put(TileType.BRICK, new Tile(atlas.cut(16 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                    TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(atlas.cut(16 * TILE_SCALE, 1 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.GRASS, new Tile(atlas.cut(17 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.WATER_1, new Tile(atlas.cut(16 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.WATER_1));
        tiles.put(TileType.WATER_2, new Tile(atlas.cut(16 * TILE_SCALE, 3 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.WATER_2));
        tiles.put(TileType.WATER_3, new Tile(atlas.cut(17 * TILE_SCALE, 3 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.WATER_3));
        tiles.put(TileType.ICE, new Tile(atlas.cut(18 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(atlas.cut(18 * TILE_SCALE, 3 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.EMPTY));
        //localDateTime = LocalDateTime.now();
        waterType = TileType.WATER_1;
        timer = new Timer(450, e -> {
            if (waterType == TileType.WATER_1){
                waterType = TileType.WATER_2;
            }
            else if (waterType == TileType.WATER_2){
                waterType = TileType.WATER_3;
            }
            else if (waterType == TileType.WATER_3){
                waterType = TileType.WATER_1;
            }
            else
                waterType = TileType.WATER_1;

        });
        timer.setRepeats(true);
        tileMap = Utils.levelParser("src/main/resources/level.txt");
        grassCoords = new ArrayList<>();
        waterCoords = new ArrayList<>();
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() == TileType.GRASS)
                    grassCoords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
                else if (tile.type() == TileType.WATER_1)
                    waterCoords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
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
    public void renderWater(Graphics2D graphics) {
        timer.start();
        if (waterType == TileType.WATER_1) {
            for (Point p : waterCoords) {
                tiles.get(TileType.WATER_2).render(graphics, p.x, p.y);
            }
        }
        else if (waterType == TileType.WATER_2) {
            for (Point p : waterCoords) {
                tiles.get(TileType.WATER_3).render(graphics, p.x, p.y);
            }
        }
        else if (waterType == TileType.WATER_3) {
            for (Point p : waterCoords) {
                tiles.get(TileType.WATER_1).render(graphics, p.x, p.y);
            }
        }
    }


    public static Map<TileType, Tile> getTiles() {
        return tiles;
    }

    public static Integer[][] getTileMap() {
        return tileMap;
    }

    public static float getPositionPlayer_X(){
        float x = 0;
        for (Integer[] integers : tileMap) {
            for (int j = 0; j < integers.length; j++) {
                if (integers[j] == 9) {
                    x = j * SCALED_TILE_SIZE;
                    break;
                }
            }
        }
        return x;
    }
    public static float getPositionPlayer_Y(){
        float y = 0;
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                if (tileMap[i][j] == 9) {
                    y = i * SCALED_TILE_SIZE;
                    break;
                }
            }
        }
        return y;
    }
}
