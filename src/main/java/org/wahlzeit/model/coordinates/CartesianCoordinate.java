package org.wahlzeit.model.coordinates;

import org.wahlzeit.annotations.PatternInstance;

import java.util.Objects;

@PatternInstance(
        patternName = "Flyweight",
        participants = {
                "AbstractCoordinate", "SphericCoordinate", "CartesianCoordinate"
        }
)
public class CartesianCoordinate extends AbstractCoordinate {

    private double x;

    private double y;

    private double z;

    /**
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static CartesianCoordinate createCartesianCoordinate(double x, double y, double z) {

        CartesianCoordinate cart = new CartesianCoordinate(x, y, z);

        CartesianCoordinate result = allCartesianCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allCartesianCoordinates) {
                result = allCartesianCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allCartesianCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        return result;
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
    public CartesianCoordinate setX(double x) throws AssertionError {
        assertClassInvariants();

        CartesianCoordinate cart = new CartesianCoordinate(x, this.y, this.z);
        CartesianCoordinate result = allCartesianCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allCartesianCoordinates) {
                result = allCartesianCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allCartesianCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        assertClassInvariants();

        return result;
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
    public CartesianCoordinate setY(double y) throws AssertionError{
        assertClassInvariants();

        CartesianCoordinate cart = new CartesianCoordinate(this.x, y, this.z);
        CartesianCoordinate result = allCartesianCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allCartesianCoordinates) {
                result = allCartesianCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allCartesianCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        assertClassInvariants();

        return result;
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
    public CartesianCoordinate setZ(double z) throws AssertionError {
        assertClassInvariants();

        CartesianCoordinate cart = new CartesianCoordinate(this.x, this.y, z);
        CartesianCoordinate result = allCartesianCoordinates.get(cart.hashCode());

        if(result == null) {
            synchronized (allCartesianCoordinates) {
                result = allCartesianCoordinates.get(cart.hashCode());
                if (result == null) {
                    result = cart;
                    allCartesianCoordinates.put(cart.hashCode(), cart);
                }
            }
        }

        assertClassInvariants();

        return result;
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

        return SphericCoordinate.createSphericCoordinate(phi, theta, radius);
    }

    @Override
    protected void assertClassInvariants() {
        // assert all variables are not NaN
        assert !Double.isNaN(this.getX()) && !Double.isNaN(this.getY()) && !Double.isNaN(this.getZ());
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
