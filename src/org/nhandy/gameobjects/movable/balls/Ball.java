package org.nhandy.gameobjects.movable.balls;

import org.nhandy.GameConstants;
import org.nhandy.GameWorld;
import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.blocks.BreakBlock;
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
    private int collisionTimer;


    private double moveSpeed = 1.0;
    private int damage = 10;


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
    public void update(Observable obs) {
        long currentTick = ((GameWorld)obs).tick;

        if(collisionTimer > (currentTick % 20)) {
            setCollidable(true);
        }

        if(isDrawable()) {
            moveForward();
            checkBorder();
            this.hitBox.setLocation(x, y);
        }

        if(!this.isCollidable()) collisionTimer++;
        //System.out.println(this);
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
        switch (random.nextInt(2)) {
            case 0: setAngle(315);
                    break;
            case 1: setAngle(225);
                    break;
        }
    }

    public void checkBorder() {
        if(isCollidable()) {
            if (x < 8) {
                x = 8;
                setAngle(angle - 90);

            }
            if (x >= GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5)) {
                x = GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5);
                setAngle(angle - 90);
            }
            if (y < 7) {
                y = 7;
                setAngle(-angle);
            }
            if (y >= GameConstants.WORLD_HEIGHT - (this.ballImage.getHeight() / 2)) {
                // TODO: send message that a ball has got past paddle. It will depend on the situation to decide what you need to do.
                // TODO: Notify that a ball has been lost
                //y = GameConstants.WORLD_HEIGHT - (this.ballImage.getHeight() / 2);
                setDrawable(false);
            }
            setCollidable(false);
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

        if(cObj instanceof BreakBlock) {
            System.out.println("Ball collided with BreakBlock");
//            GameWorld.score = ((BreakBlock) cObj).getValue();
//            ((BreakBlock) cObj).setDrawable(false);
//            setCollidable(false);
        }
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
