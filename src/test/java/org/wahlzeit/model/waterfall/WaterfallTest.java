package org.wahlzeit.model.waterfall;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WaterfallTest {

    final WaterfallType type = new WaterfallType("Root");

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSetStage() {
        Waterfall waterfall = new Waterfall(type);

        waterfall.setStages(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSetStage() {
        Waterfall waterfall = new Waterfall(type);

        waterfall.setStages(0);
    }

    @Test
    public void testPositiveSetStage() {
        Waterfall waterfall = new Waterfall(type);

        int first = waterfall.getStages();
        waterfall.setStages(2);
        int second = waterfall.getStages();

        assertEquals(first, 1);
        assertNotEquals(first, second);
    }
}
