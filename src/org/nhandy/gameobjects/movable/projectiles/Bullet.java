package org.nhandy.gameobjects.movable.projectiles;

import org.nhandy.GameConstants;
import org.nhandy.GameWorld;
import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.blocks.BreakBlock;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends MovableObject {

    private int x;
    private int y;
    private double moveSpeed = 5;

    private BufferedImage bulletImage;
    private Rectangle hitBox;
    private boolean drawable;
    private boolean collidable;

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

    @Override
    public void setCollidable(boolean canCollide) {
        this.collidable = canCollide;
    }

    @Override
    public boolean isCollidable() {
        return this.collidable;
    }

    @Override
    public void handleCollision(Collidable cObj) {
        if(cObj instanceof BreakBlock) {
            GameWorld.score = ((BreakBlock) cObj).getValue();
            ((BreakBlock) cObj).setDrawable(false);
        }
    }

    public void moveForward() {
        y -= moveSpeed;
        checkBorder();
    }

    @Override
    public void update(Observable obs) {
        if(isDrawable()) {
            moveForward();
            this.hitBox.setLocation(x, y);
        }
    }


    public void checkBorder() {
        if (x < 8) {
            x = 8;
            setDrawable(false);
        }
        if (x >= GameConstants.WORLD_WIDTH - 8) {
            x = GameConstants.WORLD_WIDTH - 8;
            setDrawable(false);
        }
        if (y < 5) {
            y = 5;
            setDrawable(false);
        }
        if (y >= GameConstants.WORLD_WIDTH - 8) {
            y = GameConstants.WORLD_WIDTH - 8;
            setDrawable(false);
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
