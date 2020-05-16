package org.nhandy.gameobjects.stationary.powerups;

import org.nhandy.gameobjects.stationary.StaticObject;

import java.awt.*;

public abstract class PowerUp extends StaticObject {
    public abstract void drawImage(Graphics g);
    public abstract Rectangle getHitBox();
}
