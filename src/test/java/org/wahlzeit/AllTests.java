package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.*;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.*;
import org.wahlzeit.services.*;
import org.wahlzeit.services.mailing.*;
import org.wahlzeit.utils.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StringUtilTest.class,
        VersionTest.class,
        EmailAddressTest.class,
        LogBuilderTest.class,
        EmailServiceTestSuite.class,
        AccessRightsTest.class,
        CoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        DatastoreAdapterTest.class,
        TellFriendTest.class
})
public class AllTests {

}
