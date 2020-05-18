package org.nhandy.gameobjects.movable;

import org.nhandy.Observable;
import org.nhandy.gameobjects.Collidable;
import org.nhandy.gameobjects.GameObject;
import org.nhandy.gameobjects.Observer;

import java.awt.*;

public abstract class MovableObject extends GameObject implements Collidable {
    public abstract void Draw(Graphics g);
    public abstract void update(Observable obv);
    public abstract Rectangle getHitBox();
}
