package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.coordinates.CartesianCoordinateTest;
import org.wahlzeit.model.coordinates.SphericCoordinateTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.model.waterfall.WaterfallManagerTest;
import org.wahlzeit.model.waterfall.WaterfallTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatastoreAdapterTest.class,
        AccessRightsTest.class,
        CartesianCoordinateTest.class,
        SphericCoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        WaterfallPhotoManagerTest.class,
        WaterfallPhotoTest.class,
        WaterfallTest.class,
        WaterfallManagerTest.class
})
public class ModelTestSuite {
}
