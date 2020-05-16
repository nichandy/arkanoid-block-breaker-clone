package org.nhandy.gameobjects.movable.vehicles;

import org.nhandy.GameConstants;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.projectiles.Bullet;
import org.nhandy.resource_loaders.Resource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tank extends MovableObject {


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private final int R = 7;
    private final int ROTATION_SPEED = 4;

    private Rectangle hitBox;

    private BufferedImage tankImage;
    private ArrayList<Bullet> ammo;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;


    public Tank(int x, int y, int vx, int vy, int angle, BufferedImage tankImage) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.tankImage = tankImage;
        this.angle = angle;
        this.hitBox = new Rectangle(x, y, this.tankImage.getWidth(), this.tankImage.getHeight());
        this.ammo = new ArrayList<>();
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() { this.ShootPressed = true;}

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() { this.ShootPressed = false;}



    public void update() {

        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        this.hitBox.setLocation(x, y);

        if (this.ShootPressed) {
            Bullet bullet = new Bullet(x,y, angle, Resource.getResourceImage("bullet"));
            this.ammo.add(bullet);
        }
        this.ammo.forEach(bullet -> bullet.update());


    }

    private void rotateLeft() {
        this.angle -= this.ROTATION_SPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATION_SPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    private void checkBorder() {
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    // x -screenw / 4
    // y - screeny / 2

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.tankImage.getWidth() / 2.0, this.tankImage.getHeight() / 2.0);
        this.ammo.forEach(bullet -> bullet.drawImage(g));
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.tankImage, rotation, null);
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x,y, this.tankImage.getWidth(), this.tankImage.getHeight());
    }



}
