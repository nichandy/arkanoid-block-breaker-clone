package org.nhandy.gameobjects.stationary.walls;


import java.awt.*;
import java.awt.image.BufferedImage;

public class unBreakWall extends Wall {
    int x, y;
    BufferedImage wallImage;
    private Rectangle hitBox;


    public unBreakWall(int x, int y, BufferedImage wallImage) {
        this.x = x;
        this.y = y;
        this.wallImage = wallImage;
        this.hitBox = new Rectangle(x, y, this.wallImage.getWidth(), this.wallImage.getHeight());
    }

    public void update(){};

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.wallImage, x, y, null);
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x,y, this.wallImage.getWidth(), this.wallImage.getHeight());
    }

    @Override
    public String toString() {
        return "unBreakWall{" +
                "x=" + x +
                ", y=" + y +
                ", wallImage=" + wallImage +
                ", hitBox=" + hitBox +
                '}';
    }
}
