package org.wahlzeit.model;

import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;

public class SphericCoordinateTest {

    @Test
    public void testAsCartesianCoordiante() {

        SphericCoordinate sphericCoordinate = new SphericCoordinate(0, 0, 1);

        CartesianCoordinate coordinate = sphericCoordinate.asCartesianCoordinate();

        throw new NotImplementedException();
    }

    @Test
    public void testGetCentralAngle() {
        throw new NotImplementedException();
    }
}
