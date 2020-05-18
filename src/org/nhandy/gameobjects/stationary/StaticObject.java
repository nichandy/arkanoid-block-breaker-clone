package org.nhandy.gameobjects.stationary;

import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.GameObject;

import java.awt.*;

public abstract class StaticObject extends GameObject implements Collidable {
    public abstract void Draw(Graphics g);
    public abstract Rectangle getHitBox();
}
