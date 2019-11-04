package org.wahlzeit.model;

import java.lang.Math;
import java.util.Objects;

public class Coordinate {

    private double x;

    private double y;

    private double z;

    /**
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Calculate Distance between itself and the given Coordinate
     * @param coordinate Coordinate the distance has to be calculated  towards
     * @return euclidean Distance in double
     */
    double getDistance(Coordinate coordinate) {
        return Math.sqrt(
                Math.pow((this.x - coordinate.getX()), 2) + Math.pow((this.y - coordinate.getY()), 2) + Math.pow((this.z - coordinate.getZ()), 2)
        );
    }

    /**
     * equals method for this class
     * @param coordinate Object this object is to be compared with
     * @return true if these objects are the same
     */
    public boolean isEqual(Coordinate coordinate) {
        if (this == coordinate) return true;
        if (coordinate == null || this.getClass() != coordinate.getClass()) return false;
        return Double.compare(coordinate.x, x) == 0 &&
                Double.compare(coordinate.y, y) == 0 &&
                Double.compare(coordinate.z, z) == 0;
    }

    @Override
    public boolean equals(Object o) {
        return this.isEqual((Coordinate) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
