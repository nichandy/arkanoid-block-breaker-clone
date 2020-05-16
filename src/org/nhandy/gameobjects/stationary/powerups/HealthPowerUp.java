package org.nhandy.gameobjects.stationary.powerups;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthPowerUp extends PowerUp {

    int x, y;
    int state = 1;
    BufferedImage healthImage;
    private Rectangle hitBox;


    public HealthPowerUp(int x, int y, BufferedImage healthImage) {
        this.x = x;
        this.y = y;
        this.healthImage = healthImage;
        this.hitBox = new Rectangle(x, y, this.healthImage.getWidth(), this.healthImage.getHeight());
    }

    public void update(){}

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    @Override
    public void drawImage(Graphics g) {
        if (state == 1) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.healthImage, x, y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x,y, this.healthImage.getWidth(), this.healthImage.getHeight());
        } else if (state == 0) {
            // don't render
        }
    }

    @Override
    public String toString() {
        return "HealthPowerUp{" +
                "x=" + x +
                ", y=" + y +
                ", state=" + state +
                ", healthImage=" + healthImage +
                ", hitBox=" + hitBox +
                '}';
    }
}
