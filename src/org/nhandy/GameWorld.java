package org.nhandy;

import org.nhandy.gameobjects.*;
import org.nhandy.gameobjects.movable.balls.Ball;
import org.nhandy.gameobjects.movable.paddles.Paddle;
import org.nhandy.gameobjects.movable.paddles.PaddleControl;
import org.nhandy.gameobjects.stationary.Background;
import org.nhandy.gameobjects.stationary.StaticObject;
import org.nhandy.resource_loaders.MapLoader;
import org.nhandy.resource_loaders.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;


/**

 * @author Nick Handy
 */

public class GameWorld extends JPanel  implements Observable{

    private static boolean paused = false;
    public static int score = 0;

//    private List<Observer> observers = new ArrayList<>();
    private int state;

    private BufferedImage world;
    private BufferedImage scoreBoard;
    private Graphics2D buffer;
    private JFrame jFrame;
    private MapLoader mapLoader;
    private Paddle paddleOne;
    private List<Observer> observers;
    private List<Collidable> collidables;
    private List<Drawable> drawables;
    private CollisionHandler collisionHandler;
    public static int framesPerSec;
    public long tick;


    public static void main(String[] args) {
        GameWorld game = new GameWorld();
        game.init();

        boolean render = false;
        double firstTime = 0.0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0.0;
        double unprocessedTime = 0.0;

        double frameTime = 0.0;
        int frames = 0;
        framesPerSec = 0;

        while (!paused) {
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= GameConstants.UPDATE_CAP) {
                unprocessedTime -= GameConstants.UPDATE_CAP;
                render = true;

                game.notifyObservers();
                game.checkCollisions();
                game.tick++;

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    framesPerSec = frames;
                    frames = 0;
                    System.out.printf("FPS: %d\n", framesPerSec);
                }


            }
            if(render) {
                game.repaint();
                frames++;

                // Add and remove observers instead of iterating through observers
                ListIterator<Drawable> itr = game.drawables.listIterator();
                while(itr.hasNext()) {
                    Drawable drawable = itr.next();
                    if(!drawable.isDrawable()) {
                        itr.remove();
                    }
                }
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }



    private void init() {
        this.jFrame = new JFrame("Doh Doh");
        this.scoreBoard = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_V_OFFSET, BufferedImage.TYPE_INT_RGB);
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        collisionHandler = new CollisionHandler();

        this.observers = Collections.synchronizedList(new ArrayList<>());
        this.collidables = Collections.synchronizedList(new ArrayList<>());
        this.drawables = Collections.synchronizedList(new ArrayList<>());

        Background background = new Background(8,8, Resource.getResourceImage("backgroundLevel1"));
        addDrawable(background);

        // Initialize Blocks
        this.mapLoader = new MapLoader();
        this.mapLoader.loadMap(drawables, collidables);


        // Initializing Paddle
        paddleOne = new Paddle(GameConstants.WORLD_WIDTH/2 - 16, GameConstants.WORLD_HEIGHT-24, Resource.getResourceImage("defaultPaddle1A"));

        PaddleControl paddleOneControl = new PaddleControl(paddleOne,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_SPACE);


        addDrawable(paddleOne);
        addCollidable(paddleOne);
        attachObserver(paddleOne);

        // Initializing Ball
        Ball ballOne = new Ball(paddleOne.getX() + 16, paddleOne.getY() - 4, Resource.getResourceImage("defaultBall"));
        addDrawable(ballOne);
        addCollidable(ballOne);
        attachObserver(ballOne);

//        ListIterator<Collidable> itr = collidables.listIterator();
//        while(itr.hasNext()) {
//            Collidable collidable = itr.next();
//            if(collidable.isCollidable()) {
//                System.out.println(collidable);
//            }
//        }

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(paddleOneControl);

        this.jFrame.setTitle("Paddles");
        this.jFrame.setSize(GameConstants.SCREEN_WIDTH + 16, GameConstants.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }


//    public void checkCollisions() {
//
//        for (Collidable cObjA : this.collidables) {
//            if (!cObjA.isCollidable()) continue;
//            if(cObjA instanceof StaticObject) continue; //IGNORE OBJECTS THAT DONT MOVE
//            for (Collidable cObjB : this.collidables) {
//                if(cObjA.equals(cObjB)) continue; // skip self collision check
//                if(!cObjB.isCollidable()) continue; // skip check if object is colliding
//                if(cObjB.getClass() == cObjA.getClass()) continue;
//                if(cObjA.getHitBox().intersects(cObjB.getHitBox())) {
//                    System.out.println("Object Collided");
//                    System.out.println(cObjA);
//                    cObjA.handleCollision(cObjB);
//                }
//            }
//        }
//
//    }

    public void checkCollisions() {
        for (Collidable cObjA : this.collidables) {
            if(!cObjA.isCollidable()) continue;
            if(cObjA instanceof StaticObject) continue;
            for(Collidable cObjB : this.collidables) {
                if(cObjA.equals(cObjB)) continue;
                if(!cObjB.isCollidable()) continue;
                if(cObjB.getClass() == cObjA.getClass()) continue;

               // System.out.println("\n\n" + "Compared Objects" + "\n" +  cObjA.getClass() + "\n" + cObjB.getClass());
                //System.out.println("\n" +  cObjA + "\n" + cObjB);
                if(cObjA.getHitBox().getBounds().intersects(cObjB.getHitBox().getBounds())) {
                    //System.out.println("Objects Collided");
                }
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) { // All painting code belongs here
        Graphics2D g2 = (Graphics2D) g;
        // Let UI Delegate paint first, which includes background filling since this component is opaque
        super.paintComponent(g2);

        // Creating graphics for ScoreBoard
        buffer = scoreBoard.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_V_OFFSET);

        // To setup scoreboard you must send buffered images to the buffer here


        /*  Creating graphics for Game World

            - Pushing world and scoreboard graphics separately
             to the same buffer makes it possible to change where the world is drawn by just changing the World Vertical Offset
            - This offset doesn't affect gameObjects who still see their rendering origin as (0,0)
        */
        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,GameConstants.WORLD_V_OFFSET, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        this.drawables.forEach(drawable -> drawable.Draw(buffer));

        g2.scale(GameConstants.WORLD_SCALE,GameConstants.WORLD_SCALE);
        g2.drawImage(scoreBoard,0,0,null);
        g2.drawImage(world,0,GameConstants.WORLD_V_OFFSET,null);

    }

    public synchronized void addCollidable(Collidable collidable) {
        this.collidables.add(collidable);
    }

    public synchronized void removeCollidable(Collidable collidable) {
        this.collidables.remove(collidable);
    }

    public synchronized void addDrawable(Drawable drawable) {
        this.drawables.add(drawable);
    }

    public synchronized void removeDrawable(Drawable drawable) {
        this.drawables.remove(drawable);
    }

    @Override
    public void setChanged() {

    }

    @Override
    public void notifyObservers() {
        for (Observer obs : this.observers) {
            obs.update(this);
        }
    }

    @Override
    public void attachObserver(Observer obs) {
        this.observers.add(obs);
    }

    @Override
    public void detachObserver(Observer obs) {
        this.observers.remove(obs);
    }
}
