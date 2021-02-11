package org.nhandy.vectormath;

/**
 * <h1>Java Arkanoid Clone for CSC 413 Term Project</h1>
 * Arkanoid is a block breaker game where the player must
 * destroy all the bricks in each level to accumulate the highest score
 * <p>
 * Additional info...
 *
 *
 * @author  Nick Handy
 * @version 1.0
 * @date   20-05-20
 *
 */
public class Vector2D {

    private double x;
    private double y;

    /**
     * Constructs a 2D vector object
     *
     * @param x defines x part of vector
     * @param y defines y part of vector
     * @return None
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return this.x; }
    public double getY() { return this.y; }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
