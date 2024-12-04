package dev.alt236.codeplasteragetestapp

import dev.alt236.codeplasterage.CodeplasterageTestRunner
import dev.alt236.codeplasterage.config.Config
import dev.alt236.codeplasterage.tests.PlasterageTestMethodCalling
import dev.alt236.codeplasteragetestapp.libcomponents.TestDummyDataFactory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(CodeplasterageTestRunner::class)
class SuperDuperMethodCallingTest : PlasterageTestMethodCalling {
    @Test
    @Config(
        includeClassNamePatterns = [TestConstants.TEST_INCLUDE_CLASSNAME_PATTERN],
        ignoreErrors = TestConstants.IGNORE_ERRORS,
        debug = TestConstants.DEBUG,
        customDummyDataFactories = [TestDummyDataFactory::class],
    )
    override fun testMethodCalling() {
        // Empty by design
    }
}
