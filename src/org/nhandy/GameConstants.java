package org.nhandy;

public class GameConstants {
    /* TODO: WORLD_WIDTH/HEIGHT = SCREEN_WIDTH/HEIGHT
             Pre-Built Map image resolution = 1152x496 pixels
             4 different map images
             vaus resolution = 16x8
             powerups resolution = 16x8 with an optional shadow of 18x9
             enemy resolution = 16x16
    * */
    public static final int WORLD_V_OFFSET = 24;
    public static final int WORLD_SCALE = 3;
    public static final int WORLD_WIDTH = 224; // src 224
    public static final int WORLD_HEIGHT = 240; // src 240
    public static final int SCREEN_WIDTH = WORLD_SCALE * WORLD_WIDTH; // predetermined world scaling of 4
    public static final int SCREEN_HEIGHT = WORLD_SCALE * (WORLD_HEIGHT + WORLD_V_OFFSET); // predetermined world scaling of 4
    public static final double UPDATE_CAP = 1.0 / 144.0;

    public enum BLOCK_TYPE {
        WHITE, ORANGE, CYAN, GREEN, RED, BLUE, PINK, YELLOW, SILVER, GOLD
    }
}
