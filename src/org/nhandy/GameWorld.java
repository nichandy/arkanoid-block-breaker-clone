package org.nhandy;

import org.nhandy.gameobjects.GameObject;
import org.nhandy.gameobjects.movable.vehicles.Tank;
import org.nhandy.gameobjects.movable.vehicles.TankControl;
import org.nhandy.resource_loaders.MapLoader;
import org.nhandy.resource_loaders.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * Main driver class of Tank Example.
 * Class is responsible for loading resources and
 * initializing game objects. Once completed, control will
 * be given to infinite loop which will act as our game loop.
 * A very simple game loop.
 * @author Nick Handy
 */

/* TODO: (1) Modify camera class two support splitscreen but do not implement it yet
         (2) Add Arkinoid game objects

 */
public class GameWorld extends JPanel  {

    private static boolean paused = false;
    public static float scale = 1.0f;

//    private List<Observer> observers = new ArrayList<>();
    private int state;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private MapLoader mapLoader;
    private Camera cameraOne;
    private Camera cameraTwo;
    public static int framesPerSec;
    ArrayList<GameObject> gameObjects;
    // List<Collidable> collidableObjects;

//    public int getState() {
//        return state;
//    }
//    public void setState(int state) {
//        this.state = state;
//        this.notifyAllObserver();
//    }
//
//    public void attach(Observer obv) {
//        this.observers.add(obv);
//    }
//
//    public void notifyAllObserver() {
//        for (Observer observer: observers) {
//            observer.update();
//        }
//    }



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

                game.gameObjects.forEach(gameObject -> gameObject.update());


                game.cameraOne.update();
                game.cameraTwo.update();

                // gameObjects.hasCollided()

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    framesPerSec = frames;
                    frames = 0;
                    //System.out.printf("FPS: %d\n", framesPerSec);
                }


            }
            if(render) {
                game.repaint();
                frames++;
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
        this.jFrame = new JFrame("Tank Rotation");
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        gameObjects = new ArrayList<>();

        this.mapLoader = new MapLoader();
        this.mapLoader.loadMap(gameObjects);

        Tank tankOne = new Tank(GameConstants.WORLD_WIDTH/2, GameConstants.WORLD_WIDTH/2, 0, 0, 0, Resource.getResourceImage("tankBlue"));
        Tank tankTwo = new Tank(GameConstants.WORLD_WIDTH - 150 ,GameConstants.WORLD_HEIGHT - 150,0,0, 0, Resource.getResourceImage("tankRed"));


        TankControl tankOneControl = new TankControl(tankOne, KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_NUMPAD0);

        TankControl tankTwoControl = new TankControl(tankTwo, KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE);

        this.gameObjects.add(tankOne);
        this.gameObjects.add(tankTwo);

        cameraOne = new Camera(tankOne, tankOne.getX(), tankOne.getY());
        cameraTwo = new Camera(tankTwo, tankTwo.getX(), tankTwo.getY());

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(tankOneControl);
        this.jFrame.addKeyListener(tankTwoControl);
        this.jFrame.setTitle("TANKS");
        this.jFrame.setSize(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }



    @Override
    public void paintComponent(Graphics g) { // All painting code belongs here
        Graphics2D g2 = (Graphics2D) g;
        // Let UI Delegate paint first, which includes background filling since this component is opaque
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT + 10);

        this.gameObjects.forEach(gameObjects -> gameObjects.drawImage(buffer));

        BufferedImage leftHalf = world.getSubimage(cameraOne.getCamX(),cameraOne.getCamY(),GameConstants.SCREEN_WIDTH/2, GameConstants.SCREEN_HEIGHT);
        BufferedImage rightHalf = world.getSubimage(cameraTwo.getCamX(),cameraTwo.getCamY(),GameConstants.SCREEN_WIDTH/2, GameConstants.SCREEN_HEIGHT);

        BufferedImage miniMap = world.getSubimage(0,0,GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);
        g2.drawImage(leftHalf,0,0,null);
        g2.drawImage(rightHalf,GameConstants.SCREEN_WIDTH/2 + 4,0,null);

        g2.scale(.1,.1);
        g2.drawImage(miniMap, 100, 7500, null);
        g2.scale(1,1);


    }
}
