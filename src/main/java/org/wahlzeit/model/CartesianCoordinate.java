package org.wahlzeit.model;

import java.lang.Math;
import java.util.Objects;

public class CartesianCoordinate implements Coordinate {

    private double x;

    private double y;

    private double z;

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
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

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Calculate Distance between itself and the given CartesianCoordinate
     * @param coordinate CartesianCoordinate the distance has to be calculated  towards
     * @return euclidean Distance in double
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();
        return Math.sqrt(
                Math.pow((this.x - coord.getX()), 2) + Math.pow((this.y - coord.getY()), 2) + Math.pow((this.z - coord.getZ()), 2)
        );
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double theta = Math.acos(
                this.z / radius
        );
        double phi = Math.asin(
                this.y / Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))
        );
        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate coord = coordinate.asSphericCoordinate();
        return coord.getCentralAngle(coord);    // TODO test
    }

    /**
     * equals method for this class
     * @param coordinate Object this object is to be compared with
     * @return true if these objects are the same
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();
        if (this == coord) return true;
        if (coord == null || this.getClass() != coord.getClass()) return false;
        return Double.compare(coord.x, x) == 0 &&
                Double.compare(coord.y, y) == 0 &&
                Double.compare(coord.z, z) == 0;
    }

    @Override
    public boolean equals(Object o) {
        return this.isEqual((CartesianCoordinate) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
