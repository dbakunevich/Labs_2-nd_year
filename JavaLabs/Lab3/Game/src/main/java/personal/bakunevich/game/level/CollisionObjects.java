package personal.bakunevich.game.level;

import personal.bakunevich.game.Game;
import personal.bakunevich.game.entity.Enemie;
import personal.bakunevich.game.entity.EntityType;
import personal.bakunevich.game.entity.Player;

import java.util.ArrayList;
import java.util.Map;

public class CollisionObjects {

    public static Map<TileType, Tile> tiles;

    public CollisionObjects() {
        tiles = Level.getTiles();
    }

    public static boolean collisionTanks(float x, float y, ArrayList<Integer> arrayEntityPositions){

        int newX1 =  (int) (x + 4) / Level.SCALED_TILE_SIZE;
        int newY1 =  (int) (y + 4) / Level.SCALED_TILE_SIZE;
        int newX2 = Math.min(15, (int) (x + Level.SCALED_TILE_SIZE - 4) / Level.SCALED_TILE_SIZE);
        int newY2 = Math.min(15, (int) (y + Level.SCALED_TILE_SIZE - 4) / Level.SCALED_TILE_SIZE);
        Tile tile1 = tiles.get(TileType.fromNumeric(Level.tileMap[newY1][newX1]));
        Tile tile2 = tiles.get(TileType.fromNumeric(Level.tileMap[newY1][newX2]));
        Tile tile3 = tiles.get(TileType.fromNumeric(Level.tileMap[newY2][newX1]));
        Tile tile4 = tiles.get(TileType.fromNumeric(Level.tileMap[newY2][newX2]));

        for (int i = 0; i < arrayEntityPositions.size() / 2; i++) {
            int first = arrayEntityPositions.get(i * 2);
            int second = arrayEntityPositions.get(i * 2 + 1);
            if (    Math.abs(first - (int) x) + 8 < Player.SPRITE_SCALE * 4 &&
                    Math.abs(second - (int) y) + 8 < Player.SPRITE_SCALE * 4 &&
                    first != (int) x && second != (int) y) {
                BoomAnimation.startBoom((int) x + 32, (int) y + 32);
                Player.isDie();
                return true;
            }
        }


        return isBadTile_Tank(tile1) || isBadTile_Tank(tile2) || isBadTile_Tank(tile3) || isBadTile_Tank(tile4);
    }
    public static boolean collisionBullets(float x, float y, int heading, ArrayList<Integer> arrayEntityPositions) {

        for (int i = 0; i < arrayEntityPositions.size() / 2; i++) {
            int first = arrayEntityPositions.get(i * 2);
            int second = arrayEntityPositions.get(i * 2 + 1);
            if (Math.abs(first + Player.SPRITE_SCALE * 2 - (int) x - 2) < Player.SPRITE_SCALE * 2 + 2 &&
                Math.abs(second + Player.SPRITE_SCALE * 2 - (int) y - 2) < Player.SPRITE_SCALE * 2 + 2) {
                if (i == 0) {
                    if (Game.checkBullet(EntityType.Player)){
                        BoomAnimation.startBoom((int) x, (int) y);
                        Player.isDie();
                    }
                }
                else {
                    if (Game.checkBullet(EntityType.Enemies)) {
                        BoomAnimation.startBoom((int) x, (int) y);
                        Enemie.isDie();
                    }
                }
            }
        }


        if (heading == 1) {
            int up = (int) y;
            int xLeft = (int) x;
            int xRight = (int) x + 4 * 4;
            int isCollision = 0;
            if (Level.tileFullSizeMap[up][xLeft]){
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[up / Level.SCALED_TILE_SIZE]
                        [xLeft / Level.SCALED_TILE_SIZE])), xLeft / Level.SCALED_TILE_SIZE, up / Level.SCALED_TILE_SIZE, (xLeft + xRight) / 2, up);
            }
            if (Level.tileFullSizeMap[up][xRight]) {
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[up / Level.SCALED_TILE_SIZE]
                        [xRight / Level.SCALED_TILE_SIZE])), xRight / Level.SCALED_TILE_SIZE, up / Level.SCALED_TILE_SIZE, (xLeft + xRight) / 2, up);
            }
            return isCollision > 0;
        }
        else if (heading == 2) {
            int right = (int) x + 4 * 4;
            int upUp = (int) y;
            int upDown = (int) y + 4 * 4;
            int isCollision = 0;
            if (Level.tileFullSizeMap[upUp][right]){
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[upUp / Level.SCALED_TILE_SIZE]
                        [right / Level.SCALED_TILE_SIZE])), right / Level.SCALED_TILE_SIZE, upUp / Level.SCALED_TILE_SIZE, right, (upUp + upDown) / 2);
            }
            if (Level.tileFullSizeMap[upDown][right]) {
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[upDown / Level.SCALED_TILE_SIZE]
                        [right / Level.SCALED_TILE_SIZE])), right / Level.SCALED_TILE_SIZE, upDown / Level.SCALED_TILE_SIZE, right, (upUp + upDown) / 2);
            }
            return isCollision > 0;

        } else if (heading == 3) {
            int up = (int) y + 4 * 4;
            int xLeft = (int) x;
            int xRight = (int) x + 4 * 4;
            int isCollision = 0;
            if (Level.tileFullSizeMap[up][xLeft]){
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[up / Level.SCALED_TILE_SIZE]
                        [xLeft / Level.SCALED_TILE_SIZE])), xLeft / Level.SCALED_TILE_SIZE, up / Level.SCALED_TILE_SIZE, (xLeft + xRight) / 2, up);
            }
            if (Level.tileFullSizeMap[up][xRight]) {
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[up / Level.SCALED_TILE_SIZE]
                        [xRight / Level.SCALED_TILE_SIZE])), xRight / Level.SCALED_TILE_SIZE, up / Level.SCALED_TILE_SIZE, (xLeft + xRight) / 2, up);
            }
            return isCollision > 0;

        } else if (heading == 4) {
            int right = (int) x - 4 * 4;
            int upUp = (int) y;
            int upDown = (int) y + 4 * 4;
            int isCollision = 0;
            if (Level.tileFullSizeMap[upUp][right]){
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[upUp / Level.SCALED_TILE_SIZE]
                        [right / Level.SCALED_TILE_SIZE])), right / Level.SCALED_TILE_SIZE, upUp / Level.SCALED_TILE_SIZE, right, (upUp + upDown) / 2);
            }
            if (Level.tileFullSizeMap[upDown][right]) {
                isCollision += isBadTile_Bullet(tiles.get(TileType.fromNumeric(Level.tileMap[upDown / Level.SCALED_TILE_SIZE]
                        [right / Level.SCALED_TILE_SIZE])), right / Level.SCALED_TILE_SIZE, upDown / Level.SCALED_TILE_SIZE, right, (upUp + upDown) / 2);
            }
            return isCollision > 0;
        }
        return true;
    }

    private static boolean isBadTile_Tank(Tile tile) {
        return tile.type().numeric() == TileType.METAL.numeric() ||
                tile.type().numeric() == TileType.WATER_1.numeric() ||
                tile.type().numeric() == TileType.NSU_1.numeric() ||
                tile.type().numeric() == TileType.NSU_2.numeric() ||
                tile.type().numeric() == TileType.BRICK.numeric() ||
                tile.type().numeric() == TileType.BRICK_1.numeric() ||
                tile.type().numeric() == TileType.BRICK_2.numeric() ||
                tile.type().numeric() == TileType.BRICK_3.numeric() ||
                tile.type().numeric() == TileType.BRICK_4.numeric() ||
                tile.type().numeric() == TileType.BRICK_5.numeric() ||
                tile.type().numeric() == TileType.BRICK_6.numeric();
    }
    private static int isBadTile_Bullet(Tile tile, int x, int y, int xBoom, int yBoom) {

        if (    tile.type().numeric() == TileType.BRICK.numeric() ||
                tile.type().numeric() == TileType.BRICK_1.numeric() ||
                tile.type().numeric() == TileType.BRICK_2.numeric() ||
                tile.type().numeric() == TileType.BRICK_3.numeric() ||
                tile.type().numeric() == TileType.BRICK_4.numeric() ||
                tile.type().numeric() == TileType.BRICK_5.numeric() ||
                tile.type().numeric() == TileType.BRICK_6.numeric()) {

            Level.updateBrickTile(tile.type().numeric(), x, y);
        }

        else if (   tile.type().numeric() == TileType.NSU_1.numeric() ||
                    tile.type().numeric() == TileType.NSU_2.numeric())
            Game.whichFinish = 1;

        if (    tile.type().numeric() == TileType.METAL.numeric() ||
                tile.type().numeric() == TileType.BRICK.numeric() ||
                tile.type().numeric() == TileType.BRICK_1.numeric() ||
                tile.type().numeric() == TileType.BRICK_2.numeric() ||
                tile.type().numeric() == TileType.BRICK_3.numeric() ||
                tile.type().numeric() == TileType.BRICK_4.numeric() ||
                tile.type().numeric() == TileType.BRICK_5.numeric() ||
                tile.type().numeric() == TileType.BRICK_6.numeric()){

            BoomAnimation.startBoom(xBoom, yBoom);
            return 1;
        }

        return 0;
    }
}
