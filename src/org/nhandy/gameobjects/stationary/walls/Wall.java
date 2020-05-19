package org.nhandy.gameobjects.stationary.walls;

import org.nhandy.gameobjects.stationary.StaticObject;

import java.awt.*;

public abstract class Wall extends StaticObject {

    public abstract void Draw(Graphics g);
    public abstract Rectangle getHitBox();

}
