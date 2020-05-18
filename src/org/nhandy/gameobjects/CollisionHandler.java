package org.nhandy.gameobjects;

import org.nhandy.gameobjects.stationary.StaticObject;

import java.util.List;

public class CollisionHandler {

    // TODO: Complete and test collisions
    public void checkCollisions(List<Collidable> collidableList) {

        for (Collidable cObjA : collidableList) {
            // if cObjA is not collidable continue
            if(cObjA instanceof StaticObject) continue; //IGNORE OBJECTS THAT DONT MOVE
            for (Collidable cObjB : collidableList) {
                if(cObjA.equals(cObjB)) continue; // skip self collision check

                if(!cObjB.isCollidable()) continue; // skip check if object is colliding
                if(cObjB.getClass() == cObjA.getClass()) continue;
                if(cObjA.getHitBox().intersects(cObjB.getHitBox().getBounds())) {
                    cObjA.handleCollision(cObjB);
                }
            }
        }

    }

}
