package dev.alt236.codeplasterage

import dev.alt236.codeplasterage.config.ConfigFactory
import dev.alt236.codeplasterage.config.TesterConfig
import dev.alt236.codeplasterage.datafactory.CustomDataFactoryFactory
import dev.alt236.codeplasterage.datafactory.DummyDataFactory
import dev.alt236.codeplasterage.datafactory.stats.DataFactoryRequestRecorder
import dev.alt236.codeplasterage.datafactory.stats.DataFactoryStatsPrinter
import dev.alt236.codeplasterage.reflection.classfinder.ClassFinder
import dev.alt236.codeplasterage.reflection.instantiation.Instantiator
import dev.alt236.codeplasterage.testers.reflection.EqualsTester
import dev.alt236.codeplasterage.testers.reflection.HashCodeTester
import dev.alt236.codeplasterage.testers.reflection.MethodCallingTester
import dev.alt236.codeplasterage.testers.reflection.ToStringTester
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import java.lang.reflect.Method

class CodeplasterageTestRunner(
    private val testClass: Class<Any>,
) : Runner() {
    private val dataFactoryStatsPrinter = DataFactoryStatsPrinter()
    private val configFactory = ConfigFactory()

    override fun getDescription(): Description = Description.createTestDescription(testClass, "***** Code Plasterage Test Runner *****")

    override fun run(notifier: RunNotifier) {
        println("${CodeplasterageTestRunner::class.java.simpleName}: Running tests on $testClass")

        val globalConfig = configFactory.getConfig(testClass)

        val testObject = testClass.getDeclaredConstructor().newInstance()
        val testMethods = testObject.collectTestMethods()

        if (globalConfig.debug) {
            println("Test methods found: $testMethods")
        }

        val unsatisfiedRequests = DataFactoryRequestRecorder()

        for (testMethod in testMethods) {
            if (testMethod.isIgnoredTest()) {
                val desc = Description.createTestDescription(testClass, testMethod.name)
                notifier.fireTestIgnored(desc)
                continue
            }

            when (testMethod.name) {
                "testEquals" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    val dataFactory = createAssembleDummyDataFactory(config, unsatisfiedRequests)
                    runTestEquals(dataFactory, config, notifier, testMethod)
                }

                "testToString" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    val dataFactory = createAssembleDummyDataFactory(config, unsatisfiedRequests)
                    runTestToString(dataFactory, config, notifier, testMethod)
                }

                "testHashCode" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    val dataFactory = createAssembleDummyDataFactory(config, unsatisfiedRequests)
                    runTestHashCode(dataFactory, config, notifier, testMethod)
                }

                "testMethodCalling" -> {
                    val config = configFactory.compileConfig(testClass, testMethod)
                    val dataFactory = createAssembleDummyDataFactory(config, unsatisfiedRequests)
                    runTestMethodCalling(dataFactory, config, notifier, testMethod)
                }

                else -> runTest(notifier, testObject, testMethod)
            }
        }

        if (globalConfig.debug) {
            dataFactoryStatsPrinter.print(unsatisfiedRequests)
        }
    }

    private fun createAssembleDummyDataFactory(
        config: TesterConfig,
        requestRecorded: DataFactoryRequestRecorder,
    ): DummyDataFactory {
        val extraFactoriesFactory = CustomDataFactoryFactory(config.debug)
        val extraFactories = extraFactoriesFactory.createSubDataFactories(config.customDummyDataFactories)
        return DummyDataFactory(config.debug, requestRecorded, extraFactories)
    }

    private fun runTestToString(
        dummyDataFactory: DummyDataFactory,
        config: TesterConfig,
        notifier: RunNotifier,
        method: Method,
    ) {
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = ToStringTester(config, testClass, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTestHashCode(
        dummyDataFactory: DummyDataFactory,
        config: TesterConfig,
        notifier: RunNotifier,
        method: Method,
    ) {
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = HashCodeTester(config, testClass, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTestEquals(
        dummyDataFactory: DummyDataFactory,
        config: TesterConfig,
        notifier: RunNotifier,
        method: Method,
    ) {
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = EqualsTester(config, testClass, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTestMethodCalling(
        dummyDataFactory: DummyDataFactory,
        config: TesterConfig,
        notifier: RunNotifier,
        method: Method,
    ) {
        val instantiator = Instantiator(dummyDataFactory, config.debug)

        val classFinder = ClassFinder(config.debug)
        val classes = classFinder.getClasses(config)

        val tester = MethodCallingTester(config, testClass, dummyDataFactory, instantiator)
        tester.test(classes, notifier, method)
    }

    private fun runTest(
        notifier: RunNotifier,
        classInstance: Any,
        method: Method,
    ) {
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
