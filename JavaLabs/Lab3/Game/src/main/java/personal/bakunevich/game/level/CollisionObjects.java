package personal.bakunevich.game.level;

import java.util.Map;

public class CollisionObjects {

    private static Integer[][] tileMap;
    private static Map<TileType, Tile> tiles;

    public CollisionObjects() {
        tileMap = Level.getTileMap();
        tiles = Level.getTiles();
    }

    public static boolean collisionObjects(float x, float y){

        int newX1 =  (int) (x + 2) / Level.SCALED_TILE_SIZE;
        int newY1 =  (int) (y + 2) / Level.SCALED_TILE_SIZE;
        int newX2 = Math.min(15, (int) (x + Level.SCALED_TILE_SIZE - 2) / Level.SCALED_TILE_SIZE);
        int newY2 = Math.min(15, (int) (y + Level.SCALED_TILE_SIZE - 2) / Level.SCALED_TILE_SIZE);
        Tile tile1 = tiles.get(TileType.fromNumeric(tileMap[newY1][newX1]));
        Tile tile2 = tiles.get(TileType.fromNumeric(tileMap[newY1][newX2]));
        Tile tile3 = tiles.get(TileType.fromNumeric(tileMap[newY2][newX1]));
        Tile tile4 = tiles.get(TileType.fromNumeric(tileMap[newY2][newX2]));
        System.out.printf("X1 = %d\t X2 = %d\t Y1 = %d\t Y2 = %d\n", newX1, newX2, newY1, newY2);

        return isBadTile(tile1) || isBadTile(tile2) || isBadTile(tile3) || isBadTile(tile4);
    }

    private static boolean isBadTile(Tile tile) {
        return tile.type().numeric() == TileType.METAL.numeric() ||
                tile.type().numeric() == TileType.WATER.numeric() ||
                tile.type().numeric() == TileType.BRICK.numeric();
    }
}
