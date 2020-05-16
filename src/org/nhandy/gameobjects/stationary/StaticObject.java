package org.nhandy.gameobjects.stationary;

import org.nhandy.gameobjects.GameObject;

import java.awt.*;

public abstract class StaticObject extends GameObject {
    public abstract void drawImage(Graphics g);
    public abstract Rectangle getHitBox();
}
