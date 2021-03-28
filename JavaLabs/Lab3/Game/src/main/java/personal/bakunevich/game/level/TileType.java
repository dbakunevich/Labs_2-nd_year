package personal.bakunevich.game.level;

public enum TileType {

    EMPTY (0),
    BRICK (1),
    METAL (2),
    GRASS (3),
    WATER (4),
    ICE (5);

    private int n;

    TileType (int n) {
        this.n = n;
    }

    public int numeric() {
        return n;
    }

    public static TileType fromNumeric(int n) {
        return switch (n) {
            case 1 -> BRICK;
            case 2 -> METAL;
            case 3 -> GRASS;
            case 4 -> WATER;
            case 5 -> ICE;
            default -> EMPTY;
        };
    }

}
