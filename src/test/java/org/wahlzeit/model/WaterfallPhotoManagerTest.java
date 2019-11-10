package org.wahlzeit.model;

import org.junit.*;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class WaterfallPhotoManagerTest {

    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Test
    public void testHasNotPhoto() {
        WaterfallPhoto photo = new WaterfallPhoto();

        boolean ret = WaterfallPhotoManager.getInstance().hasPhoto(photo.getId());

        assertFalse(ret);
    }

    /*@Test
    public void testCreatePhoto() {

        boolean ret = WaterfallPhotoManager.getInstance().createPhoto();

        assertFalse(ret);
    }*/
}
