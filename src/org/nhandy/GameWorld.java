package org.nhandy;

import org.nhandy.gameobjects.*;
import org.nhandy.gameobjects.movable.ball.Ball;
import org.nhandy.gameobjects.movable.paddle.Paddle;
import org.nhandy.gameobjects.movable.paddle.PaddleControl;
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

/* TODO: (1) Modify camera class two support splitscreen but do not implement it yet
         (2) Add Arkinoid game objects

 */
public class GameWorld extends JPanel  implements Observable{

    private static boolean paused = false;
    public static float scale = 1.0f;

//    private List<Observer> observers = new ArrayList<>();
    private int state;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private MapLoader mapLoader;
    private Paddle paddleOne;
    private List<Observer> observers;
    private List<Collidable> collidables;
    private List<Drawable> drawables;
    private CollisionHandler collisionHandler;
    public static int framesPerSec;


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
//                ListIterator<GameObject> itr = game.gameObjects.listIterator();
//                while(itr.hasNext()) {
//                    GameObject gameObject = itr.next();
//                    if(!gameObject.isDrawable()) {
//                        itr.remove();
//                    }
//                }
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
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        collisionHandler = new CollisionHandler();

        this.observers = Collections.synchronizedList(new ArrayList<>());
        this.collidables = Collections.synchronizedList(new ArrayList<>());
        this.drawables = Collections.synchronizedList(new ArrayList<>());

        Background background = new Background(0,0, Resource.getResourceImage("backgroundLevel1"));
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
        Ball ballOne = new Ball(paddleOne.getX() + 16, paddleOne.getY(), Resource.getResourceImage("defaultBall"));
        addDrawable(ballOne);
        addCollidable(ballOne);
        attachObserver(ballOne);

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


    public void checkCollisions() {

        for (Collidable cObjA : this.collidables) {
            // if cObjA is not collidable continue
            if(cObjA instanceof StaticObject) continue; //IGNORE OBJECTS THAT DONT MOVE
            for (Collidable cObjB : this.collidables) {
                if(cObjA.equals(cObjB)) continue; // skip self collision check

                if(!cObjB.isCollidable()) continue; // skip check if object is colliding
                if(cObjB.getClass() == cObjA.getClass()) continue;
                if(cObjA.getHitBox().intersects(cObjB.getHitBox().getBounds())) {
                    cObjA.handleCollision(cObjB);
                }
            }
        }

    }


    @Override
    public void paintComponent(Graphics g) { // All painting code belongs here
        Graphics2D g2 = (Graphics2D) g;
        // Let UI Delegate paint first, which includes background filling since this component is opaque
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        this.drawables.forEach(drawable -> drawable.Draw(buffer));

        g2.scale(4,4);
        g2.drawImage(world,0,0,null);

        // Multiplayer Cameras
//        BufferedImage leftHalf = world.getSubimage(cameraOne.getCamX(),cameraOne.getCamY(),GameConstants.SCREEN_WIDTH/2, GameConstants.SCREEN_HEIGHT);
//        BufferedImage rightHalf = world.getSubimage(cameraTwo.getCamX(),cameraTwo.getCamY(),GameConstants.SCREEN_WIDTH/2, GameConstants.SCREEN_HEIGHT);
//
//        BufferedImage miniMap = world.getSubimage(0,0,GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);
//        g2.drawImage(leftHalf,0,0,null);
//        g2.drawImage(rightHalf,GameConstants.SCREEN_WIDTH/2 + 4,0,null);


//        g2.scale(.1,.1);
//        g2.drawImage(miniMap, 100, 7500, null);
//        g2.scale(1,1);


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
