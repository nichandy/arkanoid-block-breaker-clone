package org.nhandy.gameobjects.hudobjects;

import org.nhandy.Observable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Score extends hudObject {
    private int x;
    private int y;
    private BufferedImage scoreImage;
    private boolean drawable;

    public Score(int x, int y, BufferedImage scoreImage) {
        this.x = x;
        this.y = y;
        this.scoreImage = scoreImage;
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
            g2d.drawImage(this.scoreImage, this.x, this.y, null);
        }
    }


    @Override
    public void update(Observable obv) {}


    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }

}
