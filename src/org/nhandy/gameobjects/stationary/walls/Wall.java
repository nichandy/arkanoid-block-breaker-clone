package org.nhandy.gameobjects.stationary.walls;

import org.nhandy.gameobjects.stationary.StationaryObject;

import java.awt.*;

public abstract class Wall extends StationaryObject {

    public abstract void Draw(Graphics g);
    public abstract Rectangle getHitBox();

}
