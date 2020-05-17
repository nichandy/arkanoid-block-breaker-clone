package org.nhandy.resource_loaders;

import org.nhandy.GameWorld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Resource {
    private static Map<String, BufferedImage> resources;
    // Hashset for synchronized maps for multiple threads

    static {
        Resource.resources = new HashMap<>();
        //Using class loaders to read in resources
        try {
            BufferedImage spritesheet = ImageIO.read(GameWorld.class.getClassLoader().getResource("spritesheet.png"));

            // Loading Game World Sprites
            Resource.resources.put("backgroundLevel1", spritesheet.getSubimage(0, 0, 224, 240));
            Resource.resources.put("backgroundLevel2", spritesheet.getSubimage(224, 0, 224, 240));
            Resource.resources.put("backgroundLevel3", spritesheet.getSubimage(448, 0, 224, 240));
            Resource.resources.put("backgroundLevel4", spritesheet.getSubimage(672, 0, 224, 240));
            Resource.resources.put("backgroundLevel5", spritesheet.getSubimage(896, 0, 224, 240));

            // Loading Paddle Sprites
            Resource.resources.put("defaultPaddle1A", spritesheet.getSubimage(560, 304, 32, 8));
            Resource.resources.put("defaultPaddle1B", spritesheet.getSubimage(592, 304, 32, 8));
            Resource.resources.put("defaultPaddle1C", spritesheet.getSubimage(624, 304, 32, 8));
            Resource.resources.put("defaultPaddle1D", spritesheet.getSubimage(656, 304, 32, 8));

            // Loading Blocks
            Resource.resources.put("defaultBlockWhite", spritesheet.getSubimage(736, 240, 16, 8));
            Resource.resources.put("defaultBlockOrange", spritesheet.getSubimage(752, 240, 16, 8));
            Resource.resources.put("defaultBlockCyan", spritesheet.getSubimage(768, 240, 16, 8));
            Resource.resources.put("defaultBlockGreen", spritesheet.getSubimage(784, 240, 16, 8));

            Resource.resources.put("defaultBlockRed", spritesheet.getSubimage(736, 248, 16, 8));
            Resource.resources.put("defaultBlockBlue", spritesheet.getSubimage(752, 248, 16, 8));
            Resource.resources.put("defaultBlockPink", spritesheet.getSubimage(768, 248, 16, 8));
            Resource.resources.put("defaultBlockYellow", spritesheet.getSubimage(784, 248, 16, 8));

            // Silver
            Resource.resources.put("defaultBlockSilver1A ", spritesheet.getSubimage(736, 256, 16, 8));
            Resource.resources.put("defaultBlockSilver1B", spritesheet.getSubimage(752, 256, 16, 8));
            Resource.resources.put("defaultBlockSilver1C", spritesheet.getSubimage(768, 256, 16, 8));
            Resource.resources.put("defaultBlockSilver1D", spritesheet.getSubimage(784, 256, 16, 8));

            // Gold
            Resource.resources.put("defaultBlockGold1A ", spritesheet.getSubimage(736, 264, 16, 8));
            Resource.resources.put("defaultBlockGold1B", spritesheet.getSubimage(752, 264, 16, 8));
            Resource.resources.put("defaultBlockGold1C", spritesheet.getSubimage(768, 264, 16, 8));
            Resource.resources.put("defaultBlockGold1D", spritesheet.getSubimage(784, 264, 16, 8));

            // Double Silver
            Resource.resources.put("defaultBlockSilver2A ", spritesheet.getSubimage(736, 272, 16, 8));
            Resource.resources.put("defaultBlockSilver2B", spritesheet.getSubimage(752, 272, 16, 8));
            Resource.resources.put("defaultBlockSilver2C", spritesheet.getSubimage(768, 272, 16, 8));
            Resource.resources.put("defaultBlockSilver2D", spritesheet.getSubimage(784, 272, 16, 8));


            // Loading Paddle Animation
            Resource.resources.put("destroyPaddle1A", spritesheet.getSubimage(560, 448, 32, 8));
            Resource.resources.put("destroyPaddle1B", spritesheet.getSubimage(592, 304, 32, 8));
            Resource.resources.put("destroyPaddle1C", spritesheet.getSubimage(624, 304, 32, 8));
            Resource.resources.put("destroyPaddle1D", spritesheet.getSubimage(656, 304, 32, 8));

            // Loading Ball Sprites
            Resource.resources.put("defaultBall", spritesheet.getSubimage(592, 384, 8, 8));
            Resource.resources.put("poweredBall", spritesheet.getSubimage(592, 384, 8, 8));

            // Loading Bullet Resource
            // TODO: Separate Left and Right Bullet
            Resource.resources.put("defaultBullet", spritesheet.getSubimage(608, 384, 3, 8)); // 5x4 starting at origin of the sprite
            Resource.resources.put("powerUpBullet1", spritesheet.getSubimage(621, 384, 3, 8));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return Resource.resources.get(key);
    }
}
