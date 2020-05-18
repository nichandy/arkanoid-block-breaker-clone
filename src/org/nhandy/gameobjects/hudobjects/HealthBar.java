package org.nhandy.gameobjects.hudobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthBar extends hudObject {

    private int x;
    private int y;
    private BufferedImage healthImage;
    private boolean drawable;

    public HealthBar(int x, int y, BufferedImage healthImage) {
        this.x = x;
        this.y = y;
        this.healthImage = healthImage;
        setDrawable(true);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void Draw(Graphics g) {
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.healthImage, this.x, this.y, null);
        }
    }

    @Override
    public void update() {}

    @Override
    public Rectangle getHitBox() {return null;}

    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }


}
