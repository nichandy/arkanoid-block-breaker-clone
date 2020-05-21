package org.nhandy.gameobjects.stationary.walls;

import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.movable.balls.Ball;
import org.nhandy.gameobjects.movable.projectiles.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class unBreakWall extends Wall {

    private int x;
    private int y;
    private BufferedImage unBreakWallImage;
    private boolean drawable;
    private boolean collidable;
    private Rectangle hitBox;

    public unBreakWall(int x, int y, BufferedImage unBreakWallImage) {
        this.x = x;
        this.y = y;
        this.unBreakWallImage = unBreakWallImage;
        this.drawable = true;
        this.collidable = true;
        this.hitBox = new Rectangle(x, y, this.unBreakWallImage.getWidth(), this.unBreakWallImage.getHeight());
    }

    public void Draw(Graphics g) {
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.unBreakWallImage, this.x, this.y, null);
            //g2d.setColor(Color.RED);
            //g2d.drawRect(x, y, this.unBreakWallImage.getWidth(), this.unBreakWallImage.getHeight());
        }

    }

    @Override
    public void update(Observable obv) {}

    @Override
    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }


    public void setCollidable(boolean canCollide) {
        this.collidable = canCollide;
    }


    public boolean isCollidable() {
        return this.collidable;
    }


    public void handleCollision(Collidable cObj) {
    }


    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }


    public boolean isDrawable() {
        return this.drawable;
    }

    @Override
    public String toString() {
        return "unBreakWall{" +
                "x=" + x +
                ", y=" + y +
                ", unBreakWallImageHeight=" + this.unBreakWallImage.getHeight() +
                ", unBreakWallImageWidth=" + this.unBreakWallImage.getWidth() +
                ", drawable=" + drawable +
                ", collidable=" + collidable +
                '}';
    }
}
