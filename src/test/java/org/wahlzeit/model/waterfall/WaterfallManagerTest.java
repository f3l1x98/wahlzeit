package org.wahlzeit.model.waterfall;

import org.junit.Test;

import static org.junit.Assert.*;

public class WaterfallManagerTest {

    @Test
    public void testCreateWaterfall() {
        try {
            WaterfallManager wm = WaterfallManager.getInstance();
            Waterfall waterfall = wm.createWaterfall(wm.rootType.getTypeName());

            assertNotNull(wm.getWaterfall(waterfall.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCreateWaterfallType() {
        try {
            WaterfallManager wm = WaterfallManager.getInstance();
            WaterfallType type = wm.createWaterfallType("NewWaterfall");

            assertNotEquals(type, wm.rootType);
            assertTrue(wm.waterfallTypes.size() > 1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCreateWaterfallTypeAlreadyExists() {
        try {
            WaterfallManager wm = WaterfallManager.getInstance();
            WaterfallType type = wm.createWaterfallType(wm.rootType.getTypeName());

            assertEquals(type, wm.rootType);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
