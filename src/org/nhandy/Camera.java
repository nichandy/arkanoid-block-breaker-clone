package org.nhandy;

import org.nhandy.gameobjects.movable.paddles.Paddle;

// TODO: Restructure camera to just support single screen or splitscreen
public class Camera {

    private int camX, camY;
    private int offsetX;
    private int offsetY;
    private Paddle paddle;

    public Camera(Paddle paddle, int camX, int camY) {
        this.offsetX = GameConstants.SCREEN_WIDTH/4;
        this.offsetY = GameConstants.SCREEN_HEIGHT/2;
        this.camX = camX - offsetX;
        this.camY = camY - offsetY;
        this.paddle = paddle;
    }

    public int getCamX() {
        return this.camX;
    }
    public int getCamY() {
        return this.camY;
    }

    public void update() {
        this.camX = paddle.getX() - offsetX;
        this.camY = paddle.getY() - offsetY;

        checkBounds();
    }

    private void checkBounds() {
        if (camX >= GameConstants.WORLD_WIDTH - (GameConstants.SCREEN_WIDTH/2)) {
            camX = GameConstants.WORLD_WIDTH - (GameConstants.SCREEN_WIDTH/2);
        } else if (camX <= 0) {
            camX = 0;
        }
        if (camY >= GameConstants.WORLD_HEIGHT - GameConstants.SCREEN_HEIGHT) {
            camY = GameConstants.WORLD_HEIGHT - GameConstants.SCREEN_HEIGHT;
        } else if (camY <= 0) {
            camY = 0;
        }

    }

    @Override
    public String toString() {
        return "Camera{" +
                "camX=" + camX +
                ", camY=" + camY +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", paddle=" + paddle +
                '}';
    }
}
