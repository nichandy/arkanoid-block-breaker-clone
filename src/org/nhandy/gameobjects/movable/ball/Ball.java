package org.nhandy.gameobjects.movable.ball;

import org.nhandy.GameConstants;
import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.powerups.PowerUp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ball extends MovableObject {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;


    private double moveSpeed = 1.0;
    private int damage = 10;

    // TODO: Ball Elements
    //      - Velocity tied to tick (Velocity is constant as it moves in open space)
    //      - Rotation
    //      - Spin?
    //      - bounce angle
    /*
            Vector Reflections
    */

    private BufferedImage ballImage;
    private Rectangle hitBox;
    private boolean drawable;
    private boolean collidable;

    public Ball(int x, int y, BufferedImage ballImage) {
        this.x = x;
        this.y = y;
        this.ballImage = ballImage;
        this.hitBox = new Rectangle(x, y, this.ballImage.getWidth() - 3, this.ballImage.getHeight()/2); // sprite is 8x8, hitbox is 5x4
        setDrawable(true);
        setCollidable(true);
        startingAngle();
    }

    @Override
    public void update(Observable obv) {
        if(isDrawable()) {
            moveForward();
            checkBorder();
            this.hitBox.setLocation(x, y);
        }
        System.out.println(this.toString());
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }

    public void moveForward() {
        this.vx = (int) Math.round(moveSpeed * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(moveSpeed * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void startingAngle() {
        Random random = new Random();
        switch (random.nextInt(1)) {
            case 0: setAngle(315);
                    break;
            case 1: setAngle(225);
                    break;
        }
    }

    public void moveLeft() {
        x -= moveSpeed;
        checkBorder();
    }

    public void moveRight() {
        x += moveSpeed;
        checkBorder();
    }


    public void checkBorder() {
        if (x < 8) {
            x = 8;
            // TODO: angle reflection based on normal in line with x axis
            setAngle(angle - 90);

        }
        if (x >= GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5)) {
            x = GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5);
            if(collidable) {
                setAngle(angle - 90);
                setCollidable(false);
                System.out.println(this.angle);
            }

            // TODO: angle reflection based on normal in line with -x axis
        }
        if (y < 7) {
            y = 7;
            setAngle(-angle);
            // TODO: angle reflection based on normal in line with -y axis

        }
        if (y >= GameConstants.WORLD_HEIGHT - (this.ballImage.getHeight() / 2)) {
            // TODO: send message that a ball has got past paddle. It will depend on the situation to decide what you need to do.
            // TODO: Notify that a ball has been lost
            //y = GameConstants.WORLD_HEIGHT - (this.ballImage.getHeight() / 2);
            setDrawable(false);
        }
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
//
        if (cObj instanceof PowerUp) {
            // alter paddle state by applying powerup
        }
    }

    @Override
    public String toString() {
        return "Ball{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", angle=" + angle +
                ", moveSpeed=" + moveSpeed +
                ", drawable=" + drawable +
                ", collidable=" + collidable +
                '}';
    }

    public void Draw(Graphics g) {
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.ballImage, this.x, this.y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x,y, this.ballImage.getWidth() - 3, this.ballImage.getHeight() / 2);
        }
    }
}
