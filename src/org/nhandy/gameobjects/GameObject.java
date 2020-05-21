package org.nhandy.gameobjects;

import org.nhandy.Observable;

import java.awt.*;

public abstract class GameObject implements Drawable, Observer {

    public abstract void update(Observable obs);

    public abstract void Draw(Graphics g);

    public abstract void setDrawable(boolean canDraw);

    public abstract boolean isDrawable();

}
