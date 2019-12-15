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
    private SphericCoordinate(double phi, double theta, double radius) {
        this.phi = normalize(phi);
        this.theta = normalize(theta);
        this.radius = radius;
    }

    public static SphericCoordinate createSphericCoordinate(double phi, double theta, double radius) {

        SphericCoordinate cart = new SphericCoordinate(phi, theta, radius);

        SphericCoordinate result = allSphericCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allSphericCoordinates) {
                result = allSphericCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allSphericCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        return result;
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
    public SphericCoordinate setPhi(double phi) throws AssertionError {
        assertClassInvariants();

        phi = normalize(phi);

        SphericCoordinate cart = new SphericCoordinate(phi, this.theta, this.radius);
        SphericCoordinate result = allSphericCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allSphericCoordinates) {
                result = allSphericCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allSphericCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        assertClassInvariants();

        return result;
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
    public SphericCoordinate setTheta(double theta) throws AssertionError {
        assertClassInvariants();

        theta = normalize(theta);

        SphericCoordinate cart = new SphericCoordinate(this.phi, theta, this.radius);
        SphericCoordinate result = allSphericCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allSphericCoordinates) {
                result = allSphericCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allSphericCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        assertClassInvariants();

        return result;
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
    public SphericCoordinate setRadius(double radius) throws AssertionError {
        assertClassInvariants();

        SphericCoordinate cart = new SphericCoordinate(this.phi, this.theta, radius);
        SphericCoordinate result = allSphericCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allSphericCoordinates) {
                result = allSphericCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allSphericCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        assertClassInvariants();

        return result;
    }

    @Override
    public CartesianCoordinate doAsCartesianCoordinate() {
        double x = this.radius * Math.sin(this.phi) * Math.cos(this.theta);
        double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        double z = this.radius * Math.cos(this.phi);

        return CartesianCoordinate.createCartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate doAsSphericCoordinate() {
        return this;
    }

    @Override
    protected void assertClassInvariants() {
        // assert all variables are not NaN
        assert !Double.isNaN(this.getPhi()) && !Double.isNaN(this.getTheta()) && !Double.isNaN(this.getRadius());
        // assert phi and theta are normalized (0 <= phi, theta < 2 * PI)
        assert this.getPhi() < (2 * Math.PI) && this.getTheta() < (2 * Math.PI);
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
