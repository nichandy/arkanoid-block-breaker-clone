package org.nhandy.gameobjects.hudobjects;

import org.nhandy.gameobjects.GameObject;

import java.awt.*;

public abstract class hudObject extends GameObject {

    public abstract void Draw(Graphics g);

    public abstract void setDrawable(boolean canDraw);

    public abstract boolean isDrawable();

}
