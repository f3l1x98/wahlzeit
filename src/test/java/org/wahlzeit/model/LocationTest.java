package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LocationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor() {
        Location newLocation = new Location(null);
    }

    @Test
    public void testValidConstrucot() {
        Location newLocation = new Location(new Coordinate(1, 2, 3));

        assertNotEquals(newLocation.coordinate, null);
    }
}
