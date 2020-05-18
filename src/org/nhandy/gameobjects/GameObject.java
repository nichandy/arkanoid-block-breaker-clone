package org.nhandy.gameobjects;

import org.nhandy.Drawable;

import java.awt.*;

public abstract class GameObject implements Drawable {

    public abstract void update();

    public abstract Rectangle getHitBox();

    public abstract void Draw(Graphics g);

    public abstract void setDrawable(boolean canDraw);

    public abstract boolean isDrawable();

}
