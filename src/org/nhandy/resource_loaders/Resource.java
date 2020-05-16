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

            Resource.resources.put("tankBlack", spritesheet.getSubimage(0, 0, 50, 50));
            Resource.resources.put("tankGreen", spritesheet.getSubimage(50, 0, 50, 50));
            Resource.resources.put("tankPink", spritesheet.getSubimage(100, 0, 50, 50));
            Resource.resources.put("tankRed", spritesheet.getSubimage(150, 0, 50, 50));
            Resource.resources.put("tankBlue", spritesheet.getSubimage(200, 0, 50, 50));

            Resource.resources.put("weaponPowerUp", spritesheet.getSubimage(64, 80, 32, 32));
            Resource.resources.put("healthPowerUp", spritesheet.getSubimage(160, 112, 32, 32));
            Resource.resources.put("shieldPowerUp", spritesheet.getSubimage(0, 144, 32, 32));

            Resource.resources.put("breakWall", spritesheet.getSubimage(0, 178, 32, 32));
            Resource.resources.put("unBreakWall", spritesheet.getSubimage(32, 178, 32, 32));
            Resource.resources.put("groundTile", spritesheet.getSubimage(64, 178, 32, 32));
            Resource.resources.put("bullet", spritesheet.getSubimage(96, 178, 24, 24));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return Resource.resources.get(key);
    }
}
