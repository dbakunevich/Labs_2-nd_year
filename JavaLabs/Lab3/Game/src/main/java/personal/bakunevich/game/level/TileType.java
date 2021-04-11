package personal.bakunevich.game.level;

public enum TileType {

    EMPTY (0),
    NSU_1 (81),
    NSU_2 (82),
    BRICK (11),
    BRICK_1 (12),
    BRICK_2 (13),
    BRICK_3 (14),
    BRICK_4 (15),
    BRICK_5 (16),
    BRICK_6 (17),
    METAL (2),
    GRASS (3),
    WATER_1(4),
    WATER_2(4),
    WATER_3(4),
    BOOM_1(77),
    BOOM_2(78),
    BOOM_3(79),
    ICE (5);

    private final int n;

    TileType (int n) {
        this.n = n;
    }

    public int numeric() {
        return n;
    }

    public static TileType fromNumeric(int n) {
        return switch (n) {
            case 81 -> NSU_1;
            case 82 -> NSU_2;
            case 11 -> BRICK;
            case 12 -> BRICK_1;
            case 13 -> BRICK_2;
            case 14 -> BRICK_3;
            case 15 -> BRICK_4;
            case 16 -> BRICK_5;
            case 17 -> BRICK_6;
            case 77 -> BOOM_1;
            case 78 -> BOOM_2;
            case 79 -> BOOM_3;
            case 2 -> METAL;
            case 3 -> GRASS;
            case 4 -> WATER_1;
            case 5 -> ICE;
            default -> EMPTY;
        };
    }

}
