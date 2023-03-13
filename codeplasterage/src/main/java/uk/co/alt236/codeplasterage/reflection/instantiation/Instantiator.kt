package uk.co.alt236.codeplasterage.reflection.instantiation

import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.log.Log
import java.lang.reflect.Constructor

internal class Instantiator(
    private val dataFactory: DummyDataFactory,
    private val debug: Boolean
) {
    private val classFilter = InstantiatorClassFilter()
    private val kotlinObjectGetter = KotlinObjectGetter(debug)

    fun <T> instantiate(clazz: Class<T>): InstantiatorResult<T> {
        return when {
            classFilter.isExcluded(clazz) -> {
                printDebug("*** EXCLUDING: $clazz")
                InstantiatorResult.Ignored("Excluded class: $clazz", clazz)
            }

            else -> instantiateInternal(clazz)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> instantiateInternal(clazz: Class<T>): InstantiatorResult<T> {
        val instances = ArrayList<T>()
        val constructors = clazz.constructors
        printDebug("Constructors: ${constructors.size}, in $clazz")

        val kotlinObjectResult = kotlinObjectGetter.getKotlinObject(clazz)

        if (kotlinObjectResult != null && kotlinObjectResult is InstantiatorResult.Success) {
            printDebug("Is Kotlin object! :$clazz")
            return kotlinObjectResult
        } else {
            if (constructors.isEmpty()) {
                return InstantiatorResult.Error(IllegalStateException("No constructors found! class=$clazz"), clazz)
            }

            for (constr in constructors) {
                val instance = if (constr.parameterCount == 0) {
                    instantiate(constr)
                } else {
                    instantiateWithDummyData(constr)
                }

                if (instance != null) {
                    instances.add(instance as T)
                }
            }

            return if (instances.isEmpty()) {
                InstantiatorResult.Error(IllegalStateException("Failed to create any instances of class=$clazz"), clazz)
            } else {
                InstantiatorResult.Success(instances, clazz)
            }
        }
    }

    private fun <T> instantiateWithDummyData(constr: Constructor<T>): T? {
        val dummyData = dataFactory.getDummyDataToCall(constr)
        return if (dummyData == null) {
            printDebug("*** FAILED TO GET PARAMS! $constr")
            null
        } else {
            val array = dummyData.toTypedArray()
            instantiate(constr, *array)
        }
    }

    private fun <T> instantiate(constr: Constructor<T>, vararg initArgs: Any?): T? {
        return try {
            printDebug("Using Constructor: $constr")
            val instance = if (initArgs.isEmpty()) {
                constr.newInstance()
            } else {
                constr.newInstance(*initArgs)
            }
            printDebug("Instantiation OK!")
            return instance
        } catch (e: Throwable) {
            Log.logE(e, constr, "While trying to instantiate with args: '[${initArgs.joinToString()}]'")
            null
        }
    }

    private fun printDebug(message: String) {
        if (debug) {
            Log.log("Instantiator", message)
        }
    }
}
