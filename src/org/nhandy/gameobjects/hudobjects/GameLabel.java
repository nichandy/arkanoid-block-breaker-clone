package org.nhandy.gameobjects.hudobjects;

import org.nhandy.Observable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLabel extends hudObject {

    private int x;
    private int y;
    private BufferedImage labelImage;
    private boolean drawable;
    private Rectangle hitBox;

    public GameLabel(int x, int y, BufferedImage labelImage) {
        this.x = x;
        this.y = y;
        this.labelImage = labelImage;
        this.drawable = true;
        this.hitBox = new Rectangle(x, y, this.labelImage.getWidth(), this.labelImage.getHeight());
    }

    public void Draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.labelImage, this.x, this.y, null);
    }

    @Override
    public void update(Observable obv) {}

    // label is not collidable

    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }
}
