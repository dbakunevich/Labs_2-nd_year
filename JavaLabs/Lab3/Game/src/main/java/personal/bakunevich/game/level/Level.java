package personal.bakunevich.game.level;

import personal.bakunevich.game.Game;
import personal.bakunevich.game.entity.Enemie;
import personal.bakunevich.game.entity.Player;
import personal.bakunevich.graphics.TextureAtlas;
import personal.bakunevich.utils.Utils;

import javax.swing.*;
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

    private static Map<TileType, Tile>  tiles;
    public static Integer[][]           tileMap;
    public static boolean[][]           tileFullSizeMap;
    private final ArrayList<Point>      grassCoords;
    private final ArrayList<Point>      waterCoords;
    private TileType                    waterType;
    private final Timer                 timer;
    public static ArrayList<Integer>    arrayEntityPositions;

    public Level(TextureAtlas atlas) {
        TextureAtlas atlasBrick1 = new TextureAtlas("brick1.png");
        TextureAtlas atlasBrick2 = new TextureAtlas("brick2.png");
        TextureAtlas atlasBrick3 = new TextureAtlas("brick3.png");
        TextureAtlas atlasBrick4 = new TextureAtlas("brick4.png");
        TextureAtlas atlasBrick5 = new TextureAtlas("brick5.png");
        TextureAtlas atlasBrick6 = new TextureAtlas("brick6.png");
        TextureAtlas atlasNSU    = new TextureAtlas("NSU.png");
        tiles = new HashMap<>();

        tiles.put(TileType.NSU_1, new Tile(atlasNSU.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.NSU_1));
        tiles.put(TileType.NSU_2, new Tile(atlasNSU.cut(TILE_SCALE, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.NSU_2));

        tiles.put(TileType.BRICK, new Tile(atlas.cut(16 * TILE_SCALE, 0, TILE_SCALE, TILE_SCALE),
                    TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.BRICK_1, new Tile(atlasBrick1.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.BRICK_1));
        tiles.put(TileType.BRICK_2, new Tile(atlasBrick2.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.BRICK_2));
        tiles.put(TileType.BRICK_3, new Tile(atlasBrick3.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.BRICK_3));
        tiles.put(TileType.BRICK_4, new Tile(atlasBrick4.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.BRICK_4));
        tiles.put(TileType.BRICK_5, new Tile(atlasBrick5.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.BRICK_5));
        tiles.put(TileType.BRICK_6, new Tile(atlasBrick6.cut(0, 0, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE, TileType.BRICK_6));


        tiles.put(TileType.METAL, new Tile(atlas.cut(16 * TILE_SCALE, TILE_SCALE, TILE_SCALE, TILE_SCALE),
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
        tileFullSizeMap = new boolean[Game.HEIGHT][Game.WIDHT];
        for (int i = 0; i < Game.HEIGHT; i++){
            for (int j = 0; j < Game.WIDHT; j++) {
                tileFullSizeMap[i][j] = true;
                tileFullSizeMap[i][j] = tileMap[i / SCALED_TILE_SIZE][j / SCALED_TILE_SIZE] != TileType.EMPTY.numeric();
                tileFullSizeMap[i][j] = tileMap[i / SCALED_TILE_SIZE][j / SCALED_TILE_SIZE] != TileType.WATER_1.numeric();
                tileFullSizeMap[i][j] = tileMap[i / SCALED_TILE_SIZE][j / SCALED_TILE_SIZE] != TileType.GRASS.numeric();
            }
        }

        arrayEntityPositions = new ArrayList<>();

    }
    public static ArrayList<Integer> getArrayEntityPositions(){
        return arrayEntityPositions;
    }

    public void update(Player player, Enemie enemie) {
        arrayEntityPositions.clear();
        arrayEntityPositions.add(player.currentPosition_X());
        arrayEntityPositions.add(player.currentPosition_Y());
        arrayEntityPositions.add(enemie.currentPosition_X());
        arrayEntityPositions.add(enemie.currentPosition_Y());
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

    public static void updateBrickTile(int numBlock, int X, int Y){
        if (numBlock == TileType.BRICK.numeric())
            tileMap[Y][X] = TileType.BRICK_1.numeric();

        else if (numBlock == TileType.BRICK_1.numeric())
            tileMap[Y][X] = TileType.BRICK_2.numeric();

        else if (numBlock == TileType.BRICK_2.numeric())
            tileMap[Y][X] = TileType.BRICK_3.numeric();

        else if (numBlock == TileType.BRICK_3.numeric())
            tileMap[Y][X] = TileType.BRICK_4.numeric();

        else if (numBlock == TileType.BRICK_4.numeric())
            tileMap[Y][X] = TileType.BRICK_5.numeric();

        else if (numBlock == TileType.BRICK_5.numeric())
            tileMap[Y][X] = TileType.BRICK_6.numeric();

        else {
            for (int i = Y; i < Y + 16; i++)
                for (int j = Y; j < X + 16; j++)
                    tileFullSizeMap[i][j] = false;
            tileMap[Y][X] = TileType.EMPTY.numeric();
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

    public static float getPositionEnemies_X(){
        float x = 0;
        for (Integer[] integers : tileMap) {
            for (int j = 0; j < integers.length; j++) {
                if (integers[j] == 8) {
                    x = j * SCALED_TILE_SIZE;
                    break;
                }
            }
        }
        return x;
    }
    public static float getPositionEnemies_Y(){
        float y = 0;
        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap[i].length; j++){
                if (tileMap[i][j] == 8) {
                    y = i * SCALED_TILE_SIZE;
                    break;
                }
            }
        }
        return y;
    }
}
