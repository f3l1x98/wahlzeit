package org.wahlzeit.model;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.waterfall.WaterfallManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WaterfallPhotoTest {

    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Test
    public void testEqualsTrue() {
        WaterfallPhoto photo1 = new WaterfallPhoto();
        WaterfallPhoto photo2 = new WaterfallPhoto();

        photo2.setWaterfall(WaterfallManager.getInstance().createWaterfall("Waterfall"));

        assertEquals(photo1, photo1);
        assertEquals(photo2, photo2);
    }

    @Test
    public void testEqualsFalse() {
        WaterfallPhoto photo1 = new WaterfallPhoto();
        WaterfallPhoto photo2 = new WaterfallPhoto();

        photo2.setWaterfall(WaterfallManager.getInstance().createWaterfall("Waterfall"));

        assertNotEquals(photo1, photo2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetWaterfallNull() {
        WaterfallPhoto photo = new WaterfallPhoto();

        photo.setWaterfall(null);
    }
}
