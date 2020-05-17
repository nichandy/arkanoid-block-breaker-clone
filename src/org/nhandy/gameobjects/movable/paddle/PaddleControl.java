/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nhandy.gameobjects.movable.paddle;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author anthony-pc
 */
public class PaddleControl implements KeyListener {

    private Paddle paddle;

    private final int right;
    private final int left;
    private final int shoot;

    public PaddleControl(Paddle paddle, int left, int right, int shoot) {
        this.paddle = paddle;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();

        if (keyPressed == left) {
            this.paddle.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.paddle.toggleRightPressed();
        }

        if (keyPressed == shoot) {
            this.paddle.toggleShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == left) {
            this.paddle.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.paddle.unToggleRightPressed();
        }
        if (keyReleased == shoot) {
            this.paddle.unToggleShootPressed();
        }

    }
}
