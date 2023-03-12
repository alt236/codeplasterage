package uk.co.alt236.codeplasterage.testers.reflection

import org.junit.runner.Description
import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.reflection.instantiation.Instantiator
import uk.co.alt236.codeplasterage.reflection.instantiation.InstantiatorResult
import uk.co.alt236.codeplasterage.testers.common.TestNotifier
import java.lang.reflect.Method

internal class EqualsTester(
    config: TesterConfig,
    private val testClass: Class<Any>,
    private val instantiator: Instantiator
) : BaseReflectiveTester(config) {

    override fun testInternal(
        classes: Collection<Class<*>>,
        notifier: TestNotifier,
        method: Method
    ) {
        val validClasses = classes.filter { it.constructors.isNotEmpty() }
        logger.log("Will run on ${validClasses.size}/${classes.size} classes")

        for ((index, clazz) in validClasses.withIndex()) {
            val text = getDescriptionText(index, classes.size, method, clazz)
            val description = Description.createTestDescription(testClass, text)

            notifier.fireTestStarted(description)
            val throwable = test(instantiator, clazz)
            notifier.fireTestFinished(description, throwable)
        }
    }

    private fun test(instantiator: Instantiator, clazz: Class<*>): Throwable? {
        return when (val result = instantiator.instantiate(clazz)) {
            is InstantiatorResult.Error -> return result.error
            is InstantiatorResult.Ignored -> return null
            is InstantiatorResult.Success -> {
                for (instance in result.instances) {
                    for (instance1 in result.instances) {
                        for (instance2 in result.instances) {
                            instance1.equalsSafe(instance2)
                        }
                    }
                }
                null
            }
        }
    }

    private fun Any.equalsSafe(other: Any?) {
        try {
            this == other
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
