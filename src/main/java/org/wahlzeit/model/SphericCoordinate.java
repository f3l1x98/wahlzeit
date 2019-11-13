package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate implements Coordinate {


    private double phi;

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
        double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();
        return this.getCartesianDistance(coord);    // TODO test
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return 0;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
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
