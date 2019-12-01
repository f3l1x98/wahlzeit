package org.wahlzeit.model.coordinates;

import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {

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
    public SphericCoordinate doAsSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double phi = Math.acos(
                this.z / radius
        );
        double theta = Math.atan(
                this.y / this.x
        );

        // in case x is 0
        if(Double.isNaN(theta))
            theta = 0;

        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
    public CartesianCoordinate doAsCartesianCoordinate() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Coordinate)
            return this.isEqual((CartesianCoordinate) o);
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
