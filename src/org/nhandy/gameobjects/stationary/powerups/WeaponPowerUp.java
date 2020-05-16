package org.nhandy.gameobjects.stationary.powerups;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WeaponPowerUp extends PowerUp{
    int x, y;
    int state = 1;
    BufferedImage weaponImage;
    private Rectangle hitBox;


    public WeaponPowerUp(int x, int y, BufferedImage weaponImage) {
        this.x = x;
        this.y = y;
        this.weaponImage = weaponImage;
        this.hitBox = new Rectangle(x, y, this.weaponImage.getWidth(), this.weaponImage.getHeight());
    }

    public void update(){}

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    @Override
    public void drawImage(Graphics g) {
        if (state == 1) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.weaponImage, x, y, null);
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x,y, this.weaponImage.getWidth(), this.weaponImage.getHeight());
        } else if (state == 0) {
            // don't render
        }
    }

    @Override
    public String toString() {
        return "WeaponPowerUp{" +
                "x=" + x +
                ", y=" + y +
                ", state=" + state +
                ", weaponImage=" + weaponImage +
                ", hitBox=" + hitBox +
                '}';
    }
}
