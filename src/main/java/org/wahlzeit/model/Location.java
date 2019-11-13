package org.wahlzeit.model;

public class Location {

    /**
     * Coordinates of the location
     */
    public CartesianCoordinate coordinate;

    /**
     * @methodtype constructor
     */
    public Location(CartesianCoordinate coordinate) {
        if(coordinate == null)
            throw new IllegalArgumentException("CartesianCoordinate must not be null!");
        this.coordinate = coordinate;
    }
}
