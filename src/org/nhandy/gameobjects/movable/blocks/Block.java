package org.nhandy.gameobjects.movable.blocks;

import org.nhandy.gameobjects.movable.MovableObject;

import java.awt.*;

public abstract class Block extends MovableObject {
    public abstract void Draw(Graphics g);

    public abstract Rectangle getHitBox();
}
