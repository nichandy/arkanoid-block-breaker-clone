package org.nhandy.gameobjects.movable.powerups;

import org.nhandy.gameobjects.movable.MovableObject;

import java.awt.*;

public abstract class PowerUp extends MovableObject {
    public abstract void drawImage(Graphics g);
    public abstract Rectangle getHitBox();
}
