package org.nhandy.gameobjects.movable.paddles;

import org.nhandy.GameConstants;
import org.nhandy.GameWorld;
import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.hudobjects.HealthBar;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.powerups.PowerUp;
import org.nhandy.gameobjects.movable.projectiles.Bullet;
import org.nhandy.resource_loaders.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Paddle extends MovableObject {

    // Paddle Current Position
    private int x;
    private int y;

    // Paddle Velocity (Vector Magnitude)
    private int velocity = 3;

    // Paddle Vector
    private int vx;
    private int vy; // Not used at the moment

    // Normal Vector
    // Will be used to compare with during collisions
    private int nx;
    private int ny;

    // Paddle Game Attributes
    private final int moveSpeed = 3;
    private int health = 3;
    private int fireRate = 30;

    //
    private Rectangle hitBox;

    private BufferedImage paddleImage;
    private ArrayList<Bullet> ammo;
    private ArrayList<HealthBar> lives;

    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private boolean collidable;
    private boolean drawable;
    private boolean weaponActive;


    public Paddle(int x, int y, BufferedImage paddleImage) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;

        this.paddleImage = paddleImage;
        this.hitBox = new Rectangle(x, y, this.paddleImage.getWidth(), this.paddleImage.getHeight());

        this.weaponActive = false;
        this.ammo = new ArrayList<>();
        this.lives = new ArrayList<>();

        this.collidable = true;
        setDrawable(true);
        setLives(health);
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



    @Override
    public void update(Observable obs) {
        // shootdelay incremented
        // Interrogate an observer to get details about its state
        long currentTick = ((GameWorld)obs).tick;

        if(isDrawable()) {
            if (this.LeftPressed) {
                this.moveLeft();
            } else if (this.RightPressed) {
                this.moveRight();
            } else {
                this.vx = 0;
            }
            this.hitBox.setLocation(x, y);

            if (isWeaponActive() && this.ShootPressed) {

                if (currentTick % fireRate == 0) {
                    this.shoot();
                }
            }
            this.ammo.forEach(bullet -> bullet.update(obs));

        }

    }

    private void setLives(int numberOfLives) {
        for (int i = 0; i < numberOfLives; i++) {
            HealthBar life = new HealthBar(10 + (i * 16), GameConstants.WORLD_HEIGHT - 12, Resource.getResourceImage("healthBar"));
            this.lives.add(life);
        }
    }

    private void addLife() {
        HealthBar life = new HealthBar(10 + ((this.lives.size()-1) * 16), GameConstants.WORLD_HEIGHT - 12, Resource.getResourceImage("healthBar"));
        this.lives.add(life);
    }

    private void removeLife() {
        this.setDrawable(false);
        if (this.lives.get(this.lives.size()-1).isDrawable()) {
            setDrawable(false);
        }
    }

    private void shoot() {
        Bullet bulletLeft = new Bullet((int) this.getHitBox().getCenterX() - 10,y, Resource.getResourceImage("defaultBullet"));
        Bullet bulletRight = new Bullet((int) this.getHitBox().getCenterX() + 10,y, Resource.getResourceImage("defaultBullet"));
        this.ammo.add(bulletLeft);
        this.ammo.add(bulletRight);
    }


    private void moveLeft() {
        this.vx = -velocity;
        this.x += this.vx;
        checkBorder();
    }

    private void moveRight() {
        this.vx = velocity;
        this.x += this.vx;
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
        if (cObj instanceof PowerUp) {
            // alter paddle state by applying powerup
        }
    }

    public boolean isWeaponActive() {
        return this.weaponActive;
    }

    @Override
    public String toString() {
        return "Paddle{" +
                "x=" + x +
                ", y=" + y +
                ", moveSpeed=" + moveSpeed +
                ", health=" + health +
                ", fireRate=" + fireRate +
                ", hitBox=" + hitBox +
                ", paddleImage=" + paddleImage +
                ", ammo=" + ammo +
                ", lives=" + lives +
                ", RightPressed=" + RightPressed +
                ", LeftPressed=" + LeftPressed +
                ", ShootPressed=" + ShootPressed +
                ", collidable=" + collidable +
                ", drawable=" + drawable +
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

    @Override
    public void Draw(Graphics g) {
        this.ammo.forEach(bullet -> bullet.Draw(g));
        this.lives.forEach(life -> life.Draw(g));
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.paddleImage, this.x, this.y, null);
            //g2d.setColor(Color.CYAN);
            //g2d.drawRect(x, y, this.paddleImage.getWidth(), this.paddleImage.getHeight());
        }
    }
}
