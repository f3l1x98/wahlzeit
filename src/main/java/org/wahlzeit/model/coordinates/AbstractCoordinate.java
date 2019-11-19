package org.wahlzeit.model.coordinates;

public abstract class AbstractCoordinate implements Coordinate {


    /**
     * @return Coordinate converted to CartesianCoordinate
     */
    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    /**
     * Calculate Distance between itself and the given CartesianCoordinate
     * @param coordinate CartesianCoordinate the distance has to be calculated  towards
     * @return euclidean Distance in double
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if(coordinate == null) throw new IllegalArgumentException("Two points needed for calculation!");
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();

        return Math.sqrt(
                Math.pow((thisCartesian.getX() - coord.getX()), 2) + Math.pow((thisCartesian.getY() - coord.getY()), 2) + Math.pow((thisCartesian.getZ() - coord.getZ()), 2)
        );
    }

    /**
     * @return Coordinate converted to SphericCoordinate
     */
    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    /**
     * Calculate central angle between the @param and this
     * @param coordinate second coordinate
     * @return central angle
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if(coordinate == null) throw new IllegalArgumentException("Two points needed for calculation!");
        SphericCoordinate thisSpheric = this.asSphericCoordinate();
        SphericCoordinate thatSpheric = coordinate.asSphericCoordinate();

        double dtheta = Math.abs(thisSpheric.getTheta() - thatSpheric.getTheta());

        double cos = Math.sin(thisSpheric.getPhi()) * Math.sin(thatSpheric.getPhi()) + Math.cos(thisSpheric.getPhi()) * Math.cos(thatSpheric.getPhi()) * Math.cos(dtheta);

        return Math.acos(cos);
    }

    @Override
    public abstract boolean isEqual(Coordinate coordinate);


    private static final double PRECISION = 1.0e-5;
    protected static boolean doubleCompare(double d1, double d2) {
        return Math.abs(d1 - d2) < PRECISION;
    }
}
