package org.wahlzeit.model.coordinates;

import org.junit.Test;
import org.wahlzeit.model.Location;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.SphericCoordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartesianCoordinateTest {

    @Test
    public void testIsEqual() {
        CartesianCoordinate coord1 = new CartesianCoordinate(1, 2, 3);
        CartesianCoordinate coord2 = new CartesianCoordinate(1, 2, 3);

        assertTrue(coord1.isEqual(coord2));
        assertTrue(coord1.isEqual(coord1));
    }

    @Test
    public void testEquals() {
        CartesianCoordinate coord1 = new CartesianCoordinate(1, 2, 3);
        CartesianCoordinate coord2 = new CartesianCoordinate(1, 2, 3);

        assertTrue(coord1.equals(coord2));
        assertTrue(coord1.equals(coord1));
        // make sure that there is no Nullpointerexception due to cast to same Coordinate type
        assertFalse(coord1.equals(new Location(coord2)));
    }

    @Test
    public void testGetCartesianDistance() {
        CartesianCoordinate coord1 = new CartesianCoordinate(1, 0, 0);
        CartesianCoordinate coord2 = new CartesianCoordinate(2, 0, 0);

        assertTrue(coord1.getCartesianDistance(coord2) == 1);

        coord1 = new CartesianCoordinate(2, 4, 1);
        coord2 = new CartesianCoordinate(4, 5, 3);

        assertTrue(coord1.getCartesianDistance(coord2) == 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistanceNull() {
        CartesianCoordinate coord1 = new CartesianCoordinate(1, 0, 0);

        coord1.getCartesianDistance(null);
    }

    @Test
    public void testAsSphericCoordinate() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 0, 1);
        CartesianCoordinate cartesianCoordinate2 = new CartesianCoordinate(3, 4, 5);

        SphericCoordinate coordinate = cartesianCoordinate.asSphericCoordinate();
        SphericCoordinate coordinate2 = cartesianCoordinate2.asSphericCoordinate();

        assertEquals(coordinate, new SphericCoordinate(0,0,1));
        assertEquals(coordinate2, new SphericCoordinate(0.7853981633974484, 0.9272952180016122, 7.0710678118654755));
    }
}
