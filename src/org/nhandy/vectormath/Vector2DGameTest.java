package org.nhandy.vectormath;

import org.nhandy.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vector2DGameTest extends JPanel implements MouseListener {

    private Graphics2D buffer;
    private JFrame jFrame;
    private BufferedImage world;
    private List<Vector2D> vectors;
    private int colorCount = 1;

    public void initScreen() {
        this.jFrame = new JFrame("Doh Doh");
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.setTitle("Paddles");
        this.jFrame.setSize(GameConstants.WORLD_WIDTH * GameConstants.WORLD_SCALE, GameConstants.WORLD_HEIGHT * GameConstants.WORLD_SCALE + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
        vectors = new ArrayList<>();

        addMouseListener(this);

        Vector2D v1 = new Vector2D(0,10);
        Vector2D v2 = new Vector2D(5,0);
        Vector2D v3 = VectorMath.add(v1, v2);
        vectors.add(v1);
        vectors.add(v2);
        vectors.add(v3);

        System.out.println("Vector 1:\t" + v1);
        System.out.println("Vector 2:\t" + v2);
        System.out.println("Vector 3:\t" + v3);
        System.out.println("Screen Dimensions:\t(" + GameConstants.SCREEN_WIDTH + " " + GameConstants.SCREEN_HEIGHT + ")");
        System.out.println("Coordinate Origin:\t(" + GameConstants.COORDINATE_ORIGIN_X + " " + GameConstants.COORDINATE_ORIGIN_Y + ")");
        System.out.println("Origin by Div:\t(" + GameConstants.WORLD_WIDTH/2 + " " + GameConstants.WORLD_WIDTH/2 + ")");

    }

    public Color getNextColor() {
        Color color;
        switch(colorCount) {
            case 1:     color = Color.RED;
                        break;
            case 2:     color = Color.YELLOW;
                        break;
            case 3:     color = Color.ORANGE;
                        break;
            case 4:     color = Color.GREEN;
                        break;
            default:    color = Color.CYAN;
                        break;
        }
        if (colorCount < 0 || colorCount > 4) {
            colorCount = 1;
        } else {
            colorCount++;
        }
        return color;
    }

    @Override
    public void paintComponent(Graphics g) { // All painting code belongs here
        Graphics2D g2 = (Graphics2D) g;
        // Let UI Delegate paint first, which includes background filling since this component is opaque
        super.paintComponent(g2);

        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        buffer.setColor(Color.GREEN);

        for (Vector2D v : vectors) {
            //buffer.setColor(getNextColor());
            Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(),6, 6);
            //Line2D.Double line = new Line2D.Double(GameConstants.WORLD_WIDTH/2,GameConstants.WORLD_HEIGHT/2,circle.getCenterX(),circle.getCenterY());
            Line2D.Double line = new Line2D.Double(0,0,circle.getCenterX(),circle.getCenterY());
            buffer.draw(line);
            buffer.draw(circle);
        }

        g2.scale(GameConstants.WORLD_SCALE, GameConstants.WORLD_SCALE);
        g2.drawImage(world,0,0,null);

    }


    public static void main(String[] args) {
        Vector2DGameTest vecTest = new Vector2DGameTest();
        vecTest.initScreen();

        while (true) {
            vecTest.repaint();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() != MouseEvent.BUTTON1) {
            return;
        }
        vectors.add(new Vector2D(e.getX() / GameConstants.WORLD_SCALE, e.getY() / GameConstants.WORLD_SCALE));
        System.out.println("Mouse Clicked: " + e.getX() / GameConstants.WORLD_SCALE + " " + e.getY() / GameConstants.WORLD_SCALE);

    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

}
