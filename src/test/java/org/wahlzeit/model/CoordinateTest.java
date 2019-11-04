package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    @Test
    public void testEquals() {
        Coordinate coord1 = new Coordinate(1, 2, 3);
        Coordinate coord2 = new Coordinate(1, 2, 3);

        assertTrue(coord1.isEqual(coord2));
        assertTrue(coord1.isEqual(coord1));

        assertTrue(coord1.equals(coord2));
        assertTrue(coord1.equals(coord1));
    }

    @Test
    public void testDistance() {
        Coordinate coord1 = new Coordinate(1, 0, 0);
        Coordinate coord2 = new Coordinate(2, 0, 0);

        assertTrue(coord1.getDistance(coord2) == 1);

        coord1 = new Coordinate(2, 4, 1);
        coord2 = new Coordinate(4, 5, 3);

        assertTrue(coord1.getDistance(coord2) == 3);
    }
}
