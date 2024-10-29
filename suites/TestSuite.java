package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        src.tests.ChangeAppConditionTests.class,
        src.tests.ArticleTests.class,
        src.tests.GetStartedTest.class,
        src.tests.MyListsTests.class,
        src.tests.SearchTests.class
})

public class TestSuite {}
