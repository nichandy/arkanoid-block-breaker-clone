package org.nhandy.gameobjects.movable.blocks;

import org.nhandy.GameConstants;
import org.nhandy.GameWorld;
import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakBlock extends Block {

    private int x;
    private int y;
    private int value;
    private BufferedImage breakBlockImage;
    private boolean drawable;
    private boolean collidable;
    private GameConstants.BLOCK_TYPE blockType;
    private Rectangle hitBox;
    private boolean destroyed;

    public BreakBlock(int x, int y, BufferedImage breakBlockImage) {
        this.x = x;
        this.y = y;
        this.breakBlockImage = breakBlockImage;
        this.drawable = true;
        this.collidable = true;
        blockType = GameConstants.BLOCK_TYPE.WHITE;
        this.setValue(blockType);
        this.hitBox = new Rectangle(x, y, this.breakBlockImage.getWidth(), this.breakBlockImage.getHeight());
    }

    public BreakBlock(int x, int y, GameConstants.BLOCK_TYPE blockType, BufferedImage breakBlockImage) {
        this.x = x;
        this.y = y;
        this.breakBlockImage = breakBlockImage;
        this.drawable = true;
        this.collidable = true;
        this.blockType = blockType;
        this.setValue(blockType);
        this.hitBox = new Rectangle(x, y, this.breakBlockImage.getWidth(), this.breakBlockImage.getHeight());
    }

    public void Draw(Graphics g) {
        if(isDrawable()) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.breakBlockImage, this.x, this.y, null);
//            g2d.setColor(Color.RED);
//            g2d.drawRect(x, y, this.breakBlockImage.getWidth(), this.breakBlockImage.getHeight());
        }

    }

    @Override
    public void update(Observable obs) {
        if (!isDrawable()) {
            //GameWorld.score += this.getValue();
        }
    }

    @Override
    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    private void setValue(GameConstants.BLOCK_TYPE block_type) {
        switch (block_type) {
            case WHITE:     this.value = 50;
                            break;
            case ORANGE:    this.value = 60;
                            break;
            case CYAN:      this.value = 70;
                            break;
            case GREEN:     this.value = 80;
                            break;
            case RED:       this.value = 90;
                            break;
            case BLUE:      this.value = 100;
                            break;
            case PINK:      this.value = 110;
                            break;
            case YELLOW:    this.value = 120;
                            break;
        }
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setCollidable(boolean canCollide) {
        this.collidable = canCollide;
    }


    public boolean isCollidable() {
        return this.collidable;
    }


    public void handleCollision(Collidable cObj) {}


    public void setDrawable(boolean canDraw) {
        this.drawable = canDraw;
    }


    public boolean isDrawable() {
        return this.drawable;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "BreakBlock{" +
                "x=" + x +
                ", y=" + y +
                ", breakBlockImageHeight=" + this.breakBlockImage.getHeight() +
                ", breakBlockImageWidth=" + this.breakBlockImage.getWidth() +
                ", drawable=" + drawable +
                ", collidable=" + collidable +
                '}';
    }
}
