package uk.co.alt236.codeplasteragetestapp

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.alt236.codeplasterage.CodeplasterageTestRunner
import uk.co.alt236.codeplasterage.config.Config
import uk.co.alt236.codeplasterage.tests.PlasterageTests
import uk.co.alt236.codeplasteragetestapp.libcomponents.TestDummyDataFactory

@RunWith(CodeplasterageTestRunner::class)
@Config(
    includeClassNamePatterns = [TestConstants.TEST_INCLUDE_CLASSNAME_PATTERN],
    ignoreErrors = TestConstants.IGNORE_ERRORS,
    debug = TestConstants.DEBUG,
    customDummyDataFactories = [TestDummyDataFactory::class]
)
class SuperDuperAllTests : PlasterageTests {

    @Test
    override fun testToString() {
        // Empty by design
    }

    @Test
    override fun testHashCode() {
        // Empty by design
    }

    @Test
    override fun testEquals() {
        // Empty by design
    }

    @Test
    override fun testMethodCalling() {
        // Empty by design
    }

    @Test
    fun testMethodCalling2() {
        assertTrue(true)
    }
}
