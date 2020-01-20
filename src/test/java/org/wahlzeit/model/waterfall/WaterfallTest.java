package org.wahlzeit.model.waterfall;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WaterfallTest {

    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

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
