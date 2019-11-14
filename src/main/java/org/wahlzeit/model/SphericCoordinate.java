package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {


    /**
     * The angle in the around the z-axis in radians
     */
    private double phi;

    /**
     * The angle in the x-y-plane in radians
     */
    private double theta;

    private double radius;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    /**
     * @methodtype get
     */
    public double getPhi() {
        return phi;
    }

    /**
     * @methodtype set
     */
    public void setPhi(double phi) {
        this.phi = phi;
    }

    /**
     * @methodtype get
     */
    public double getTheta() {
        return theta;
    }

    /**
     * @methodtype set
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.sin(this.phi) * Math.cos(this.theta);
        double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        double z = this.radius * Math.cos(this.phi);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null) return false;
        SphericCoordinate coord = coordinate.asSphericCoordinate();
        if (this == coord) return true;
        if (coord == null || this.getClass() != coord.getClass()) return false;
        return Double.compare(coord.phi, phi) == 0 &&
                Double.compare(coord.theta, theta) == 0 &&
                Double.compare(coord.radius, radius) == 0;
    }

    @Override
    public boolean equals(Object o) {
        return isEqual((SphericCoordinate) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius);
    }
}
