package org.wahlzeit.model.coordinates;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {


    /**
     * The angle in the around the z-axis in radians with 0 <= phi <= PI
     */
    private double phi;

    /**
     * The angle in the x-y-plane in radians with 0 <= theta <= 2 * PI
     */
    private double theta;

    private double radius;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = normalize(phi);
        this.theta = normalize(theta);
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
        this.phi = normalize(phi);
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
        this.theta = normalize(theta);
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
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null) return false;
        SphericCoordinate coord = coordinate.asSphericCoordinate();
        if (this == coord) return true;
        if (coord == null || this.getClass() != coord.getClass()) return false;
        return AbstractCoordinate.doubleCompare(coord.phi, phi) &&
                AbstractCoordinate.doubleCompare(coord.theta, theta) &&
                AbstractCoordinate.doubleCompare(coord.radius, radius);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Coordinate)
            return isEqual((SphericCoordinate) o);
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius);
    }

    /**
     * removes the multiplier of 2 * PI from input radians (not needed and makes equal comp easier)
     * @param radian input from which the multiplier is to be removed
     * @return input mod 2 * PI
     */
    protected  double normalize(double radian) {
        double normalized = radian % (2 * Math.PI);
        normalized = (normalized + (2 * Math.PI)) % (2 * Math.PI);
        return normalized <= Math.PI ? normalized : normalized - (2 * Math.PI);
    }

}
