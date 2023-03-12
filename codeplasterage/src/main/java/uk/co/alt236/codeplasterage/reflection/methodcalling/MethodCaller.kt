package uk.co.alt236.codeplasterage.reflection.methodcalling

import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.log.Log
import java.lang.reflect.Method

internal class MethodCaller(
    val dummyDataFactory: DummyDataFactory,
    val debug: Boolean
) {

    fun <T> callMethod(callable: CallableMethod.StaticMethod<T>) = callMethodUnsafe(null, callable)
    fun <T> callMethod(instance: T, callable: CallableMethod<T>) = callMethodUnsafe(instance, callable)

    private fun <T> callMethodUnsafe(instance: T?, callable: CallableMethod<T>): CallResult<T> {
        val method = callable.method
        printDebug("About to call Method: `$method`. Declared in $callable")
        return if (method.parameterCount == 0) {
            invoke(instance, method)
        } else {
            invokeWithDummyData(instance, method)
        }
    }

    private fun <T> invokeWithDummyData(instance: T?, method: Method): CallResult<T> {
        val dummyData = dummyDataFactory.getDummyDataToCall(method)
        return if (dummyData == null) {
            printDebug("*** FAILED TO GET PARAMS! $method")
            CallResult.Error(IllegalStateException("Failed to get Dummy params!"))
        } else {
            val array = dummyData.toTypedArray()
            invoke(instance, method, *array)
        }
    }

    private fun <T> invoke(instance: T?, method: Method, vararg initArgs: Any?): CallResult<T> {
        return try {
            printDebug("Calling Method: $method")
            val result = if (initArgs.isEmpty()) {
                method.invoke(instance)
            } else {
                method.invoke(instance, *initArgs)
            }
            printDebug("Method Invocation OK!")
            return CallResult.Success(result)
        } catch (e: Throwable) {
            Log.logE(e, method, "While trying to invoke with args: '[${initArgs.joinToString()}]'")
            CallResult.Error(e)
        }
    }

    private fun printDebug(message: String) {
        if (debug) {
            Log.log("MethodCaller", message)
        }
    }
}
