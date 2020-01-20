package org.wahlzeit.model.waterfall;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

public class WaterfallManagerTest {

    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

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

    @Test(expected = AssertionError.class)
    public void testCreateWaterfallInvalidType() {
        try {
            WaterfallManager wm = WaterfallManager.getInstance();
            Waterfall waterfall = wm.createWaterfall("BliBlaBlub");

            fail();
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
