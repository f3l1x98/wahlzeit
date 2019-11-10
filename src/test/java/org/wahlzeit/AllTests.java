package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.*;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.*;
import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StringUtilTest.class,
        VersionTest.class,
        TellFriendTest.class,
        ModelTestSuite.class,
        ServiceTestSuite.class
})
public class AllTests {
}
