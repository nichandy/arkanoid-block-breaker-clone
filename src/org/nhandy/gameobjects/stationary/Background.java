package org.nhandy.gameobjects.stationary;

import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends StaticObject {
    private int x;
    private int y;
    private BufferedImage backgroundImage;
    private boolean drawable;

    public Background(int x, int y, BufferedImage backgroundImage) {
        this.x = x;
        this.y = y;
        this.backgroundImage = backgroundImage;
        this.drawable = true;
    }

    public void Draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.backgroundImage, x, y, null);
    }

    @Override
    public void update(Observable obv) {}

    // Background is not collidable
    @Override
    public Rectangle getHitBox() {
        return null;
    }

    @Override
    public void setCollidable(boolean canCollide) {}

    @Override
    public boolean isCollidable() { return false; }

    @Override
    public void handleCollision(Collidable cObj) {}

    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }
}
