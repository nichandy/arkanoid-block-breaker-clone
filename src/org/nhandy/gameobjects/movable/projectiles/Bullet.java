package org.nhandy.gameobjects.movable.projectiles;

import org.nhandy.GameConstants;
import org.nhandy.gameobjects.movable.MovableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends MovableObject {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    int R = 12;
    BufferedImage bulletImage;
    Rectangle hitBox;

    public Bullet(int x, int y, int angle, BufferedImage bulletImage) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.bulletImage = bulletImage;
        this.hitBox = new Rectangle(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    public void moveForward() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    public void update() {
        moveForward();
        checkBorder();
        this.hitBox.setLocation(x, y);
    }

    public void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.WORLD_WIDTH - 88) {
            x = GameConstants.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.WORLD_WIDTH - 80) {
            y = GameConstants.WORLD_WIDTH - 80;
        }
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", angle=" + angle +
                ", R=" + R +
                ", bulletImage=" + bulletImage +
                ", hitBox=" + hitBox +
                '}';
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.bulletImage.getWidth() / 2.0, this.bulletImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.bulletImage, rotation, null);
        g2d.drawRect(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x,y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }

}
