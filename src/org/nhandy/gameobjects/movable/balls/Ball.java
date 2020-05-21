package org.nhandy.gameobjects.movable.balls;

import org.nhandy.GameConstants;
import org.nhandy.GameWorld;
import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.movable.MovableObject;
import org.nhandy.gameobjects.movable.blocks.BreakBlock;
import org.nhandy.gameobjects.movable.paddles.Paddle;
import org.nhandy.gameobjects.movable.powerups.PowerUp;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends MovableObject {

    // Ball Position (x,y)
    private int x;
    private int y;

    // Ball Velocity Vector (vx,vy)
    private int vx;
    private int vy;

    // Ball Velocity
    private int velocity = 1;


    private int angle;


    private int collisionTimer;




    private BufferedImage ballImage;
    private Rectangle hitBox;
    private boolean drawable;
    private boolean collidable;

    public Ball(int x, int y, BufferedImage ballImage) {
        this.x = x;
        this.y = y;

        this.vx = -1;
        this.vy = -1;

        this.ballImage = ballImage;
        this.hitBox = new Rectangle(this.x, this.y, this.ballImage.getWidth() - 3, this.ballImage.getHeight()/2); // sprite is 8x8, hitbox is 5x4


        setDrawable(true);
        setCollidable(true);
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
            //System.out.println("X: " + this.x + " Y: " + this.y + " Vx: " + this.vx + " Vy: " + this.vy + " Velo: " + velocity + " Angle: " + angle);
            this.hitBox.setLocation(x, y);
        } else {
            //System.out.println("Ball Lost: Notify Paddle to Remove Life");

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

    private void moveForward() {
        this.vx *= velocity;
        this.vy *= velocity;
        x += vx;
        y += vy;
        checkBorder();
    }


    // Computes dot product of two vectors
    private int dotProduct(int vx1, int vy1, int vx2, int vy2) {
        return (vx1 * vx2) + (vy1 * vy2);
    }

    // Calculates reflection of angle for a single axis
    private int calcReflection(int vectorAxis, int normalAxis, int dotP) {
        return vectorAxis - 2 * dotP * normalAxis;
    }

    // Takes velocity vector of Ball and normal vector of object to compute
    // reflection to collision with object
    private void nonAxisAlignedCollision(int vx1, int vy1, int nx1, int ny1) {
        int dp = dotProduct(vx1, vy1, nx1, ny1);
        this.vx = calcReflection(vx1, nx1, dp);
        this.vy = calcReflection(vy1, ny1, dp);
    }

    private void axisAlignedCollision(char axis) {
        switch (axis) {
            case 'H':   this.vy *= -1;
                        break;
            case 'V':   this.vx *= -1;
                        break;
        }
    }

    private void checkBorder() {
        if(isCollidable()) {
            if (x < 8) {
                x = 8;
                axisAlignedCollision('V');

            }
            if (x >= GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5)) {
                x = GameConstants.WORLD_WIDTH - (this.ballImage.getWidth() + 5);
                axisAlignedCollision('V');

            }
            if (y < 7) {
                y = 7;
                axisAlignedCollision('H');

            }
            if (y >= GameConstants.WORLD_HEIGHT - (this.ballImage.getHeight() / 2)) {
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

        if(cObj instanceof Paddle) {

            if(cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_LEFT || cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_RIGHT) {
                axisAlignedCollision('V');
            }
            if(cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_BOTTOM || cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_TOP) {
                axisAlignedCollision('H');
            }
        }

        if(cObj instanceof BreakBlock) {
            //System.out.println("Ball collided with BreakBlock");

            if(cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_LEFT || cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_RIGHT) {
                axisAlignedCollision('V');
            }
            if(cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_BOTTOM || cObj.getHitBox().outcode(this.x, this.y) == Rectangle.OUT_TOP) {
                axisAlignedCollision('H');
            }
            ((BreakBlock) cObj).setDrawable(false);
            if (!((BreakBlock) cObj).isDrawable()) {
                GameWorld.score += ((BreakBlock) cObj).getValue();
            }
            cObj.setCollidable(false);
            setCollidable(false);
            //System.out.println("Score: " + GameWorld.score + " Block Type: " + ((BreakBlock) cObj).getValue());
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
                ", velocity=" + velocity +
                ", collisionTimer=" + collisionTimer +
                ", ballImage=" + ballImage +
                ", hitBox=" + hitBox +
                ", drawable=" + drawable +
                ", collidable=" + collidable +
                '}';
    }

    public void Draw(Graphics g) {
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.ballImage, this.x, this.y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(this.x, this.y, this.ballImage.getWidth() - 3, this.ballImage.getHeight() / 2);
        }
    }
}
