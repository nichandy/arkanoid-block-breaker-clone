package org.nhandy.gameobjects;

import java.awt.*;

public interface Collidable {


    public Rectangle getHitBox();

    public void setCollidable(boolean canCollide);

    public boolean isCollidable();

    public void handleCollision(Collidable cObj);


}
