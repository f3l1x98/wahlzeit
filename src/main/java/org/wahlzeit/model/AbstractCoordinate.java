package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {


    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    /**
     * Calculate Distance between itself and the given CartesianCoordinate
     * @param coordinate CartesianCoordinate the distance has to be calculated  towards
     * @return euclidean Distance in double
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if(coordinate == null) return Double.NaN;
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();

        return Math.sqrt(
                Math.pow((thisCartesian.getX() - coord.getX()), 2) + Math.pow((thisCartesian.getY() - coord.getY()), 2) + Math.pow((thisCartesian.getZ() - coord.getZ()), 2)
        );
    }

    /**
     * Convert to sphericCoordinate (basic impl checks if the coordinate is already a spheric one and otherwise returns null)
     * @return Coordinate converted to spheric Coordinate or null
     */
    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if(coordinate == null) return Double.NaN;
        SphericCoordinate thisSpheric = this.asSphericCoordinate();
        SphericCoordinate coord = coordinate.asSphericCoordinate();

        double dtheta = Math.abs(thisSpheric.getTheta() - coord.getTheta());

        double numerator = Math.sqrt(
                Math.pow((Math.cos(coord.getPhi() * Math.sin(dtheta))), 2) +
                        Math.pow((Math.cos(thisSpheric.getPhi()) * Math.sin(coord.getPhi()) - Math.sin(thisSpheric.getPhi()) * Math.cos(coord.getPhi()) * Math.cos(dtheta / 2)), 2)
        );

        double denominator = Math.sin(thisSpheric.getPhi()) * Math.sin(coord.getPhi()) + Math.cos(thisSpheric.getPhi()) * Math.cos(coord.getPhi()) * Math.cos(dtheta);

        return Math.atan(numerator / denominator);
    }

    @Override
    public abstract boolean isEqual(Coordinate coordinate);


    private static final double PRECISION = 1.0e-5;
    protected static boolean doubleCompare(double d1, double d2) {
        return Math.abs(d1 - d2) < PRECISION;
    }
}
