package org.nhandy.gameobjects.stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends StaticObject {
    int x, y;
    BufferedImage backgroundImage;

    public Background(int x, int y, BufferedImage backgroundImage) {
        this.x = x;
        this.y = y;
        this.backgroundImage = backgroundImage;
    }

    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.backgroundImage, x, y, null);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getHitBox() {
        return null;
    }
}
