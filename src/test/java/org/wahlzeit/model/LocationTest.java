package org.wahlzeit.model;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class LocationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor() {
        Location newLocation = new Location(null);
    }

    @Test
    public void testValidConstrucot() {
        try{
            Location newLocation = new Location(new CartesianCoordinate(1, 2, 3));
            Location newLocation2 = new Location(new SphericCoordinate(6, 5, 4));

            assertNotEquals(newLocation.coordinate, null);
            assertNotEquals(newLocation2.coordinate, null);
        } catch (Exception e) {
            fail("Constructor didn't accept argument");
        }
    }
}
