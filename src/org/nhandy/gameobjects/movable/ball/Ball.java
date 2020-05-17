package org.nhandy.gameobjects.movable.ball;

import org.nhandy.GameConstants;
import org.nhandy.gameobjects.movable.MovableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends MovableObject {
    private int x;
    private int y;
    private int moveSpeed = 1;
    private int damage = 10;

    BufferedImage ballImage;
    Rectangle hitBox;

    public Ball(int x, int y, BufferedImage ballImage) {
        this.x = x;
        this.y = y;
        this.ballImage = ballImage;
        this.hitBox = new Rectangle(x, y, this.ballImage.getWidth() - 3, this.ballImage.getHeight()/2); // sprite is 8x8, hitbox is 5x4
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    public void moveUp() {
        y -= moveSpeed;
        checkBorder();
    }

    public void moveDown() {
        y += moveSpeed;
        checkBorder();
    }

    public void moveLeft() {
        x -= moveSpeed;
        checkBorder();
    }

    public void moveRight() {
        x += moveSpeed;
        checkBorder();
    }



    public void update() {
        moveUp();
        checkBorder();
        this.hitBox.setLocation(x, y);
        //System.out.println(this);
    }

    public void checkBorder() {
        if (x < 8) {
            x = 8;
        }
        if (x >= GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5)) {
            x = GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5);
        }
        if (y < 8) {
            y = 8;
        }
        if (y >= GameConstants.WORLD_HEIGHT - (this.ballImage.getHeight() / 2)) {
            // TODO: send message that a ball has got past paddle. It will depend on the situation to decide what you need to do.
        }
    }

    @Override
    public String toString() {
        return "ball{" +
                "x=" + x +
                ", y=" + y +
                ", ballImage=" + ballImage +
                ", hitBox=" + hitBox +
                '}';
    }

    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.ballImage, this.x, this.y, null);
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x,y, this.ballImage.getWidth() - 3, this.ballImage.getHeight() / 2);
    }
}
