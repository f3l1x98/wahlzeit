package org.wahlzeit.model;

import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {

    @Test
    public void testEquals() {
        SphericCoordinate coord1 = new SphericCoordinate(1, 2, 3);
        SphericCoordinate coord2 = new SphericCoordinate(1.0, 2.0, 3.0);
        SphericCoordinate coord3 = new SphericCoordinate((1.0 + (Math.PI * 2)), (2.0 + (Math.PI * 2) * 2), 3.0);

        assertTrue(coord1.isEqual(coord2));
        assertTrue(coord1.isEqual(coord1));
        assertTrue(coord2.isEqual(coord3));

        assertTrue(coord1.equals(coord2));
        assertTrue(coord1.equals(coord1));
    }

    @Test
    public void testAsCartesianCoordiante() {

        SphericCoordinate sphericCoordinate = new SphericCoordinate(0, 0, 1);
        SphericCoordinate sphericCoordinate2 = new SphericCoordinate(30, 60, 5);

        CartesianCoordinate coordinate = sphericCoordinate.asCartesianCoordinate();
        CartesianCoordinate coordinate2 = sphericCoordinate2.asCartesianCoordinate();

        assertEquals(coordinate, new CartesianCoordinate(0,0,1));
        assertEquals(coordinate2, new CartesianCoordinate(4.705070719233549, 1.5058126650418855, 0.7712572494379202));
    }

    @Test(expected = NotImplementedException.class)
    public void testGetCentralAngle() {
        throw new NotImplementedException();
    }
}
