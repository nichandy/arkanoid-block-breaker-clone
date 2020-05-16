package org.nhandy.gameobjects.movable;

import org.nhandy.gameobjects.GameObject;

import java.awt.*;

public abstract class MovableObject extends GameObject {
    public abstract void drawImage(Graphics g);
    public abstract void update();
    public abstract Rectangle getHitBox();
}
