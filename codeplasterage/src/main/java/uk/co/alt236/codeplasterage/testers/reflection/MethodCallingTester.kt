package uk.co.alt236.codeplasterage.testers.reflection

import org.junit.runner.Description
import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.reflection.instantiation.Instantiator
import uk.co.alt236.codeplasterage.reflection.instantiation.InstantiatorResult
import uk.co.alt236.codeplasterage.reflection.methodcalling.CallResult
import uk.co.alt236.codeplasterage.reflection.methodcalling.CallableMethod
import uk.co.alt236.codeplasterage.reflection.methodcalling.MethodCaller
import uk.co.alt236.codeplasterage.reflection.methodcalling.MethodFinder
import uk.co.alt236.codeplasterage.testers.common.TestNotifier
import java.lang.reflect.Method

internal class MethodCallingTester(
    config: TesterConfig,
    private val testClass: Class<Any>,
    private val dummyDataFactory: DummyDataFactory,
    private val instantiator: Instantiator,
) : BaseReflectiveTester(config) {

    override fun testInternal(
        classes: Collection<Class<*>>,
        notifier: TestNotifier,
        method: Method,
    ) {
        val methodCaller = MethodCaller(dummyDataFactory, config.debug)
        val methodFinder = MethodFinder(config.debug)
        val callableMethods = classes.collectMethods(methodFinder)

        logger.log("Will execute ${callableMethods.size} method(s) from ${classes.size} class(es)")

        for ((index, callableMethod) in callableMethods.withIndex()) {
            val methodSignature = "#${callableMethod.method.name}"

            val text = getDescriptionText(index, callableMethods.size, method, callableMethod.clazz, methodSignature)
            val description = Description.createTestDescription(testClass, text)

            notifier.fireTestStarted(description)
            val throwable = test(instantiator, methodCaller, callableMethod)
            notifier.fireTestFinished(description, throwable)
        }
    }

    private fun Collection<Class<*>>.collectMethods(methodFinder: MethodFinder): List<CallableMethod<*>> {
        val result = ArrayList<CallableMethod<*>>()

        for (clazz in this) {
            result.addAll(methodFinder.getCallableMethods(clazz))
        }

        return result
    }

    private fun <T> test(
        instantiator: Instantiator,
        methodCaller: MethodCaller,
        callableMethod: CallableMethod<T>,
    ): Throwable? {
        return when (callableMethod) {
            is CallableMethod.StaticMethod -> testStaticMethod(methodCaller, callableMethod)
            is CallableMethod.InstanceMethod -> when (val result = instantiator.instantiate(callableMethod.clazz)) {
                is InstantiatorResult.Error -> return result.error
                is InstantiatorResult.Ignored -> return null
                is InstantiatorResult.Success -> testInstanceMethod(methodCaller, result.instances, callableMethod)
            }
        }
    }

    private fun <T> testStaticMethod(
        methodCaller: MethodCaller,
        callableMethod: CallableMethod.StaticMethod<T>,
    ): Throwable? {
        val result = methodCaller.callMethod(callableMethod)
        return if (result is CallResult.Error) {
            result.error
        } else {
            null
        }
    }

    private fun <T> testInstanceMethod(
        methodCaller: MethodCaller,
        instances: List<T>,
        callableMethod: CallableMethod.InstanceMethod<T>,
    ): Throwable? {
        for (instance in instances) {
            try {
                val result = methodCaller.callMethod(instance, callableMethod)
                if (result is CallResult.Error) {
                    return result.error
                }
            } catch (t: Throwable) {
                return t
            }
        }
        return null
    }
}
