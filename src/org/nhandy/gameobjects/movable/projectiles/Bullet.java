package org.nhandy.gameobjects.movable.projectiles;

import org.nhandy.GameConstants;
import org.nhandy.gameobjects.movable.MovableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends MovableObject {

    private int x;
    private int y;
    private double moveSpeed = 5;

    private BufferedImage bulletImage;
    private Rectangle hitBox;
    private boolean drawable;

    public Bullet(int x, int y, BufferedImage bulletImage) {
        this.x = x;
        this.y = y;
        this.bulletImage = bulletImage;
        this.hitBox = new Rectangle(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
        setDrawable(true);
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    public void moveForward() {
        y -= moveSpeed;
        checkBorder();
    }

    public void update() {
        if(isDrawable()) {
            moveForward();
            checkBorder();
            this.hitBox.setLocation(x, y);
        }
        //System.out.println(this);
    }

    public void checkBorder() {
        if (x < 8) {
            x = 8;
        }
        if (x >= GameConstants.WORLD_WIDTH - 8) {
            x = GameConstants.WORLD_WIDTH - 8;
        }
        if (y < 8) {
            y = 8;
        }
        if (y >= GameConstants.WORLD_WIDTH - 8) {
            y = GameConstants.WORLD_WIDTH - 8;
        }
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", bulletImage=" + bulletImage +
                ", hitBox=" + hitBox +
                '}';
    }

    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }


    public void Draw(Graphics g) {
        if(this.isDrawable()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.bulletImage, this.x, this.y, null);
        }
//        g2d.drawRect(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
//        g2d.setColor(Color.CYAN);
//        g2d.drawRect(x,y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }

}
