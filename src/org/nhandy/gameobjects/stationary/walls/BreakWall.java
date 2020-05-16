package org.nhandy.gameobjects.stationary.walls;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakWall extends Wall {
    int x, y;
    int state = 2;
    BufferedImage wallImage;
    private Rectangle hitBox;


    public BreakWall(int x, int y, BufferedImage wallImage) {
        this.x = x;
        this.y = y;
        this.wallImage = wallImage;
        this.hitBox = new Rectangle(x, y, this.wallImage.getWidth(), this.wallImage.getHeight());
    }

    public void update(){}

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    @Override
    public void drawImage(Graphics g) {
        if (state == 2) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.wallImage, x, y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x,y, this.wallImage.getWidth(), this.wallImage.getHeight());
        } else if (state == 1) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.wallImage, x, y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x,y, this.wallImage.getWidth(), this.wallImage.getHeight());
        }
    }

    @Override
    public String toString() {
        return "BreakWall{" +
                "x=" + x +
                ", y=" + y +
                ", state=" + state +
                ", wallImage=" + wallImage +
                ", hitBox=" + hitBox +
                '}';
    }
}
