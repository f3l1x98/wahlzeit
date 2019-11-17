package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {

    @Test
    public void testIsEqual() {
        SphericCoordinate coord1 = new SphericCoordinate(1, 2, 3);
        SphericCoordinate coord2 = new SphericCoordinate(1.0, 2.0, 3.0);
        SphericCoordinate coord3 = new SphericCoordinate((1.0 + (Math.PI * 2)), (2.0 + (Math.PI * 2) * 2), 3.0);

        assertTrue(coord1.isEqual(coord2));
        assertTrue(coord1.isEqual(coord1));
        assertTrue(coord2.isEqual(coord3));
    }

    @Test
    public void testEquals() {
        SphericCoordinate coord1 = new SphericCoordinate(1, 2, 3);
        SphericCoordinate coord2 = new SphericCoordinate(1.0, 2.0, 3.0);
        SphericCoordinate coord3 = new SphericCoordinate((1.0 + (Math.PI * 2)), (2.0 + (Math.PI * 2) * 2), 3.0);

        assertTrue(coord1.equals(coord2));
        assertTrue(coord1.equals(coord1));
        assertTrue(coord2.equals(coord3));
        // make sure that there is no Nullpointerexception due to cast to same Coordinate type
        assertFalse(coord1.equals(new Location(coord3)));
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngleNull() {
        SphericCoordinate coord1 = new SphericCoordinate(1, 0, 0);

        coord1.getCentralAngle(null);
    }

    @Test
    public void testGetCentralAngleSameCoordinate() {

        SphericCoordinate coordinate = new SphericCoordinate(Math.PI, Math.PI, 10);

        double angle = coordinate.getCentralAngle(coordinate);

        assertTrue(AbstractCoordinate.doubleCompare(angle, 0));
    }

    @Test
    public void testGetCentralAngleSameTheta() {

        SphericCoordinate thisSpheric = new SphericCoordinate(Math.PI, Math.PI, 10);
        SphericCoordinate thatSpheric = new SphericCoordinate(2 * Math.PI, Math.PI, 10);

        double angle = thisSpheric.getCentralAngle(thatSpheric);

        // if theta doesn't change the central angle always equals the difference between the phis
        assertTrue(AbstractCoordinate.doubleCompare(angle, Math.abs(thatSpheric.getPhi() - thisSpheric.getPhi())));
    }

    @Test
    public void testGetCentralAngleSamePhi() {

        SphericCoordinate thisSpheric = new SphericCoordinate(Math.PI, Math.PI, 10);
        SphericCoordinate thatSpheric = new SphericCoordinate(Math.PI, 2 * Math.PI, 10);

        double angle = thisSpheric.getCentralAngle(thatSpheric);

        // if phi doesn't change the central angle always equals the difference between the thetas
        assertTrue(AbstractCoordinate.doubleCompare(angle, Math.abs(thatSpheric.getTheta() - thisSpheric.getTheta())));
    }

    @Test
    public void testGetCentralAngle() {

        SphericCoordinate thisSpheric = new SphericCoordinate(Math.PI / 2, Math.PI / 2, 10);
        SphericCoordinate thatSpheric = new SphericCoordinate(Math.PI, Math.PI, 10);
        SphericCoordinate thatSpheric2 = new SphericCoordinate( 2, 1.2, 10);

        double angle = thisSpheric.getCentralAngle(thatSpheric);
        double angle2 = thisSpheric.getCentralAngle(thatSpheric2);

        assertTrue(AbstractCoordinate.doubleCompare(angle, 1.57079632679));
        assertTrue(AbstractCoordinate.doubleCompare(angle2, 0.429203673205));
    }
}
