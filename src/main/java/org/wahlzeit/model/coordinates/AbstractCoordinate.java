package org.wahlzeit.model.coordinates;

public abstract class AbstractCoordinate implements Coordinate {


    /**
     * @return Coordinate converted to CartesianCoordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        assertClassInvariants();

        CartesianCoordinate result = doAsCartesianCoordinate();

        assert result != null;

        assertClassInvariants();

        return result;
    }

    public abstract CartesianCoordinate doAsCartesianCoordinate();

    /**
     * Calculate Distance between itself and the given CartesianCoordinate
     * @param coordinate CartesianCoordinate the distance has to be calculated  towards
     * @return euclidean Distance in double
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertIsNonNullArgument(coordinate);

        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();

        double result = Math.sqrt(
                Math.pow((thisCartesian.getX() - coord.getX()), 2) + Math.pow((thisCartesian.getY() - coord.getY()), 2) + Math.pow((thisCartesian.getZ() - coord.getZ()), 2)
        );

        assertIsNotNaN(result);

        return result;
    }

    /**
     * @return Coordinate converted to SphericCoordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {

        assertClassInvariants();

        SphericCoordinate result = doAsSphericCoordinate();

        assert result != null;

        assertClassInvariants();

        return result;
    }

    public abstract SphericCoordinate doAsSphericCoordinate();

    /**
     * Calculate central angle between the @param and this
     * @param coordinate second coordinate
     * @return central angle
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        assertIsNonNullArgument(coordinate);

        SphericCoordinate thisSpheric = this.asSphericCoordinate();
        SphericCoordinate thatSpheric = coordinate.asSphericCoordinate();

        double dtheta = Math.abs(thisSpheric.getTheta() - thatSpheric.getTheta());

        double cos = Math.sin(thisSpheric.getPhi()) * Math.sin(thatSpheric.getPhi()) + Math.cos(thisSpheric.getPhi()) * Math.cos(thatSpheric.getPhi()) * Math.cos(dtheta);

        double result = Math.acos(cos);

        assertIsNotNaN(result);

        return result;
    }

    /**
     * equals method for the subclasses of this abstract class,
     * which compares the CartesianCoordinate representation of the two Coordinates
     * @param coordinate Object this object is to be compared with
     * @return true if these objects are the same
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        assertIsNonNullArgument(coordinate);

        CartesianCoordinate thatCoord = coordinate.asCartesianCoordinate();
        CartesianCoordinate thisCoord = coordinate.asCartesianCoordinate();
        if (this == thatCoord) return true;
        return AbstractCoordinate.doubleCompare(thisCoord.getX(), thatCoord.getX()) &&
                AbstractCoordinate.doubleCompare(thisCoord.getY(), thatCoord.getY()) &&
                AbstractCoordinate.doubleCompare(thisCoord.getZ(), thatCoord.getZ());
    }


    private static final double PRECISION = 1.0e-5;
    protected static boolean doubleCompare(double d1, double d2) {
        return Math.abs(d1 - d2) < PRECISION;
    }

    protected void assertIsNonNullArgument(Object c) {
        if(c == null)
            throw new IllegalArgumentException("Argument must not be null!");
    }

    protected void assertIsNotNaN(double d) {
        if(Double.isNaN(d))
            throw new ArithmeticException("Double was NaN!");
    }

    protected abstract void assertClassInvariants();
}
