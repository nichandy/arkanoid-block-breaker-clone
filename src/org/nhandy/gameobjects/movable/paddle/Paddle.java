package org.nhandy.gameobjects.movable.paddle;

import org.nhandy.GameConstants;
import org.nhandy.gameobjects.hudobjects.HealthBar;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.projectiles.Bullet;
import org.nhandy.resource_loaders.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Paddle extends MovableObject {


    private int x;
    private int y;
    private final int moveSpeed = 3;
    private boolean drawable;
    private int fireRate;
    // shootDelay?
    // some way to keep track of time?

    private Rectangle hitBox;

    private BufferedImage paddleImage;
    private ArrayList<Bullet> ammo;
    private ArrayList<HealthBar> lives;

    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;


    public Paddle(int x, int y, BufferedImage paddleImage) {
        this.x = x;
        this.y = y;
        this.paddleImage = paddleImage;
        this.hitBox = new Rectangle(x, y, this.paddleImage.getWidth(), this.paddleImage.getHeight());
        this.ammo = new ArrayList<>();
        this.lives = new ArrayList<>();
        setDrawable(true);
        setLives(3);
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }


    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() { this.ShootPressed = true;}

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() { this.ShootPressed = false;}



    public void update() {

        if(isDrawable()) {
            if (this.LeftPressed) {
                this.moveLeft();
            }
            if (this.RightPressed) {
                this.moveRight();
            }
            this.hitBox.setLocation(x, y);

            if (this.ShootPressed ) {
                Bullet bulletLeft = new Bullet((int) this.getHitBox().getCenterX() - 10,y, Resource.getResourceImage("defaultBullet"));
                Bullet bulletRight = new Bullet((int) this.getHitBox().getCenterX() + 10,y, Resource.getResourceImage("defaultBullet"));
                this.ammo.add(bulletLeft);
                this.ammo.add(bulletRight);

                this.lives.get(this.lives.size() - 1).setDrawable(false);
            }
            this.ammo.forEach(bullet -> bullet.update());

            //System.out.println(this);
        }

    }

    public void setLives(int numberOfLives) {
        for (int i = 0; i < numberOfLives; i++) {
            HealthBar life = new HealthBar(10 + (i * 16), GameConstants.WORLD_HEIGHT - 12, Resource.getResourceImage("healthBar"));
            this.lives.add(life);
        }
    }


    private void moveLeft() {
        this.x -= moveSpeed;
        checkBorder();
    }

    private void moveRight() {
        this.x += moveSpeed;
        checkBorder();
    }

    private void checkBorder() {
        if (x < 8) {
            x = 8;
        }
        if (x >= GameConstants.WORLD_WIDTH - (this.paddleImage.getWidth() + 8)) {
            x = GameConstants.WORLD_WIDTH - (this.paddleImage.getWidth() + 8);
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
        return "x=" + x + ", y=" + y;
    }

    @Override
    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }

    @Override
    public boolean isDrawable() {
        return this.drawable;
    }

    @Override
    public void Draw(Graphics g) {
        this.ammo.forEach(bullet -> bullet.Draw(g));
        this.lives.forEach(life -> life.Draw(g));
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.paddleImage, this.x, this.y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x, y, this.paddleImage.getWidth(), this.paddleImage.getHeight());
        }
    }

}
