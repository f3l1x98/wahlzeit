package org.wahlzeit.model;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

public class WaterfallPhotoTest {

    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSetStage() {
        WaterfallPhoto photo = new WaterfallPhoto();

        photo.setStages(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSetStage() {
        WaterfallPhoto photo = new WaterfallPhoto();

        photo.setStages(0);
    }

    @Test
    public void testPositiveSetStage() {
        WaterfallPhoto photo = new WaterfallPhoto();

        int first = photo.getStages();
        photo.setStages(2);
        int second = photo.getStages();

        assertEquals(first, 1);
        assertNotEquals(first, second);
    }
}
