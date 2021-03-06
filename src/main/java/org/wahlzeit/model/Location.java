package org.wahlzeit.model;

import org.wahlzeit.model.coordinates.Coordinate;

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
            throw new IllegalArgumentException("Coordinate must not be null!");
        this.coordinate = coordinate;
    }
}
