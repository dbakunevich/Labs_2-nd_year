package personal.bakunevich.game.level;

import java.util.Map;

public class CollisionObjects {

    private static Integer[][] tileMap;
    private static Map<TileType, Tile> tiles;

    public CollisionObjects() {
        tileMap = Level.getTileMap();
        tiles = Level.getTiles();
    }

    public static boolean collisionTanks(float x, float y){

        int newX1 =  (int) (x + 2) / Level.SCALED_TILE_SIZE;
        int newY1 =  (int) (y + 2) / Level.SCALED_TILE_SIZE;
        int newX2 = Math.min(15, (int) (x + Level.SCALED_TILE_SIZE - 2) / Level.SCALED_TILE_SIZE);
        int newY2 = Math.min(15, (int) (y + Level.SCALED_TILE_SIZE - 2) / Level.SCALED_TILE_SIZE);
        Tile tile1 = tiles.get(TileType.fromNumeric(tileMap[newY1][newX1]));
        Tile tile2 = tiles.get(TileType.fromNumeric(tileMap[newY1][newX2]));
        Tile tile3 = tiles.get(TileType.fromNumeric(tileMap[newY2][newX1]));
        Tile tile4 = tiles.get(TileType.fromNumeric(tileMap[newY2][newX2]));


        return isBadTile_Tank(tile1) || isBadTile_Tank(tile2) || isBadTile_Tank(tile3) || isBadTile_Tank(tile4);
    }
    public static boolean collisionBullets(float x, float y, int heading){
        if (heading == 1){
            int newY =  (int) (y) / Level.SCALED_TILE_SIZE;
            int newX1 =  (int) x / Level.SCALED_TILE_SIZE;
            int newX2 =  (int) (x + Level.SCALED_TILE_SIZE) / Level.SCALED_TILE_SIZE;
            if (isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX1]))) &&
                    isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX2])))){
                return true;
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX1])))){
                int newX = (int) (x - 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX2])))){
                int newX = (int) (x + 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else return false;
        }
        else if (heading == 2){
            int newX = Math.min(15, (int) (x + Level.SCALED_TILE_SIZE - 41) / Level.SCALED_TILE_SIZE);

            int newY1 =  (int) y / Level.SCALED_TILE_SIZE;
            int newY2 =  (int) (y + Level.SCALED_TILE_SIZE) / Level.SCALED_TILE_SIZE;
            if (isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY1][newX]))) &&
                    isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY2][newX])))){
                return true;
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY1][newX])))){
                int newY = (int) (y - 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY2][newX])))){
                int newY = (int) (y + 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else return false;
        }
        else if (heading == 3){
            int newY = Math.min(15, (int) (y + Level.SCALED_TILE_SIZE - 41) / Level.SCALED_TILE_SIZE);

            int newX1 =  (int) x / Level.SCALED_TILE_SIZE;
            int newX2 =  (int) (x + Level.SCALED_TILE_SIZE) / Level.SCALED_TILE_SIZE;
            if (isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX1]))) &&
                isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX2])))){
                return true;
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX1])))){
                int newX = (int) (x - 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX2])))){
                int newX = (int) (x + 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else return false;
        }
        else if (heading == 4) {
            int newX = (int) (x) / Level.SCALED_TILE_SIZE;
            int newY1 =  (int) y / Level.SCALED_TILE_SIZE;
            int newY2 =  (int) (y + Level.SCALED_TILE_SIZE) / Level.SCALED_TILE_SIZE;
            if (isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY1][newX]))) &&
                    isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY2][newX])))){
                return true;
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY1][newX])))){
                int newY = (int) (y - 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else if(isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY2][newX])))){
                int newY = (int) (y + 17) / Level.SCALED_TILE_SIZE;
                return isBadTile_Bullet(tiles.get(TileType.fromNumeric(tileMap[newY][newX])));
            }
            else return false;
        }
        return true;
    }

    private static boolean isBadTile_Tank(Tile tile) {
        return tile.type().numeric() == TileType.METAL.numeric() ||
                tile.type().numeric() == TileType.WATER_1.numeric() ||
                tile.type().numeric() == TileType.BRICK.numeric();
    }
    private static boolean isBadTile_Bullet(Tile tile) {
        return tile.type().numeric() == TileType.METAL.numeric() ||
                tile.type().numeric() == TileType.BRICK.numeric();
    }
}
