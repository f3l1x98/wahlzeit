package org.wahlzeit.model;

public class Location {

    /**
     * Coordinates of the location
     */
    public Coordinate coordinate;

    /**
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        if(coordinate == null)
            throw new IllegalArgumentException("CartesianCoordinate must not be null!");
        this.coordinate = coordinate;
    }
}
