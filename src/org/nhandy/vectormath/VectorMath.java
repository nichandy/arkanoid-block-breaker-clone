package org.nhandy.vectormath;

public interface VectorMath {

    /**
     * Add this vector to another
     *
     * @param v is the vector to be added
     * @return this the resulting vector
     */
    public static Vector2D add(Vector2D v1, Vector2D v2) {
         return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Subtract this vector by another
     *
     * @param v is the vector to be subtracted
     * @return this the resulting vector
     */
    public static Vector2D sub(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Divide this vector by a number
     *
     * @param d number the vector is divided by
     * @return this the resulting vector
     */
    public static Vector2D div(Vector2D v, double d) {
        return new Vector2D(v.getX() / d, v.getY() / d);
    }

    /**
     * Divide this vector by a number
     *
     * @param d number the vector is divided by
     * @return this the resulting vector
     */
    public static double dot(Vector2D v1, Vector2D v2) {
        return (v1.getX() * v2.getX()) + (v1.getY() * v2.getY());
    }

    /**
     * Computes length of the vector
     *
     * @return length the resulting length of the vector
     */
    public static double length(Vector2D v) {
        return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
    }

    /**
     * Scales the vector by a constant value
     *
     * @param scale scale value
     * @return this the resulting vector
     */
    public static Vector2D scale(Vector2D v, double scale) {
        return new Vector2D(v.getX() * scale, v.getY() * scale);
    }

    /**
     * Computes the normal of the vector
     *
     * @return normalized the resulting normal vector
     */
    public static Vector2D normal(Vector2D v) {
        return div(v, length(v));
    }

    /**
     * Computes the normal of the vector
     *
     * @return normalized the resulting normal vector
     */
    public static Vector2D toPolar(Vector2D v) {
        return new Vector2D(length(v), Math.atan(v.getY() / v.getX()));
    }


}
