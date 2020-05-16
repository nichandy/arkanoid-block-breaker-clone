package org.nhandy.gameobjects.stationary.powerups;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShieldPowerUp extends PowerUp {
    int x, y;
    int state = 1;
    BufferedImage shieldImage;
    private Rectangle hitBox;


    public ShieldPowerUp(int x, int y, BufferedImage shieldImage) {
        this.x = x;
        this.y = y;
        this.shieldImage = shieldImage;
        this.hitBox = new Rectangle(x, y, this.shieldImage.getWidth(), this.shieldImage.getHeight());
    }

    public void update(){}

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }


    @Override
    public void drawImage(Graphics g) {
        if (state == 1) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.shieldImage, x, y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x,y, this.shieldImage.getWidth(), this.shieldImage.getHeight());
        } else if (state == 0) {
            // don't render
        }
    }

    @Override
    public String toString() {
        return "ShieldPowerUp{" +
                "x=" + x +
                ", y=" + y +
                ", state=" + state +
                ", shieldImage=" + shieldImage +
                ", hitBox=" + hitBox +
                '}';
    }
}
