package uk.co.alt236.codeplasterage

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import uk.co.alt236.codeplasterage.config.ConfigFactory
import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.reflection.classfinder.ClassFinder
import uk.co.alt236.codeplasterage.reflection.instantiation.Instantiator
import uk.co.alt236.codeplasterage.testers.reflection.EqualsTester
import uk.co.alt236.codeplasterage.testers.reflection.HashCodeTester
import uk.co.alt236.codeplasterage.testers.reflection.MethodCallingTester
import uk.co.alt236.codeplasterage.testers.reflection.ToStringTester
import java.lang.reflect.Method

class CodeplasterageTestRunner(private val testClass: Class<Any>) : Runner() {
    private val configFactory = ConfigFactory()

    override fun getDescription(): Description {
        return Description.createTestDescription(testClass, "***** Code Plasterage Test Runner *****")
    }

    override fun run(notifier: RunNotifier) {
        println("${CodeplasterageTestRunner::class.java.simpleName}: Running tests on $testClass")

        val globalConfig = configFactory.getConfig(testClass)

        val testObject = testClass.getDeclaredConstructor().newInstance()
        val testMethods = testObject.collectTestMethods()

        if (globalConfig.debug) {
            println("Test methods found: $testMethods")
        }

        for (testMethod in testMethods) {
            if (testMethod.isIgnoredTest()) {
                val desc = Description.createTestDescription(testClass, testMethod.name)
                notifier.fireTestIgnored(desc)
                continue
            }

            when (testMethod.name) {
                "testEquals" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    runTestEquals(config, notifier, testMethod)
                }

                "testToString" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    runTestToString(config, notifier, testMethod)
                }

                "testHashCode" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    runTestHashCode(config, notifier, testMethod)
                }

                "testMethodCalling" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    runTestMethodCalling(config, notifier, testMethod)
                }

                else -> runTest(notifier, testObject, testMethod)
            }
        }
    }

    private fun runTestToString(config: TesterConfig, notifier: RunNotifier, method: Method) {
        val dummyDataFactory = DummyDataFactory(config.debug)
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = ToStringTester(config, testClass, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTestHashCode(config: TesterConfig, notifier: RunNotifier, method: Method) {
        val dummyDataFactory = DummyDataFactory(config.debug)
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = HashCodeTester(config, testClass, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTestEquals(config: TesterConfig, notifier: RunNotifier, method: Method) {
        val dummyDataFactory = DummyDataFactory(config.debug)
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = EqualsTester(config, testClass, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTestMethodCalling(config: TesterConfig, notifier: RunNotifier, method: Method) {
        val dummyDataFactory = DummyDataFactory(config.debug)
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = MethodCallingTester(config, testClass, dummyDataFactory, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTest(notifier: RunNotifier, classInstance: Any, method: Method) {
        val description = Description.createTestDescription(testClass, method.name)

        notifier.fireTestStarted(description)
        try {
            method.invoke(classInstance)
            notifier.fireTestFinished(description)
        } catch (t: Throwable) {
            notifier.fireTestFailure(Failure(description, t))
        }
    }

    private companion object {
        private fun Any.collectTestMethods() = this.javaClass.methods.filter { it.isValidTestMethod() }

        private fun Method.isValidTestMethod() = this.isAnnotationPresent(Test::class.java)

        private fun Method.isIgnoredTest() = this.isAnnotationPresent(Ignore::class.java)
    }
}
