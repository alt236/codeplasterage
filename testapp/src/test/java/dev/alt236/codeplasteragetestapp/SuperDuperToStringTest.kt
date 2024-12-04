package dev.alt236.codeplasteragetestapp

import dev.alt236.codeplasterage.CodeplasterageTestRunner
import dev.alt236.codeplasterage.config.Config
import dev.alt236.codeplasterage.tests.PlasterageTestToString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(CodeplasterageTestRunner::class)
class SuperDuperToStringTest : PlasterageTestToString {
    @Test
    @Config(
        includeClassNamePatterns = [TestConstants.TEST_INCLUDE_CLASSNAME_PATTERN],
        ignoreErrors = TestConstants.IGNORE_ERRORS,
        debug = TestConstants.DEBUG,
    )
    override fun testToString() {
        // Empty by design
    }
}
