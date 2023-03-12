package uk.co.alt236.codeplasteragetestapp

import org.junit.Test
import org.junit.runner.RunWith
import uk.co.alt236.codeplasterage.CodeplasterageTestRunner
import uk.co.alt236.codeplasterage.config.Config
import uk.co.alt236.codeplasterage.tests.PlasterageTestMethodCalling

@RunWith(CodeplasterageTestRunner::class)
class SuperDuperMethodCallingTest : PlasterageTestMethodCalling {

    @Test
    @Config(
        includeClassNamePatterns = [TestConstants.TEST_INCLUDE_CLASSNAME_PATTERN],
        ignoreErrors = TestConstants.IGNORE_ERRORS,
        debug = TestConstants.DEBUG
    )
    override fun testMethodCalling() {
        // Empty by design
    }
}
