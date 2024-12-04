package dev.alt236.codeplasterage.datafactory

import dev.alt236.codeplasterage.log.Log
import kotlin.reflect.KClass

class CustomDataFactoryFactory(
    private val debug: Boolean,
) {
    fun createSubDataFactories(classes: List<KClass<out SubDataFactory>>): List<SubDataFactory> {
        val result = ArrayList<SubDataFactory>()
        for (clazz in classes) {
            result.add(instantiate(clazz))
        }

        return result
    }

    private fun instantiate(clazz: KClass<out SubDataFactory>): SubDataFactory {
        try {
            val constructor = clazz.java.getConstructor(Boolean::class.java)
            return constructor.newInstance(debug)
        } catch (e: NoSuchMethodException) {
            val message =
                "Trying to instantiate custom ${SubDataFactory::class.java.simpleName}. " +
                    "Please ensure that '${clazz.simpleName}' has a constructor that takes a single boolean " +
                    "(to enable debugging) as a parameter"
            Log.logE(throwable = e, clazz = clazz, context = message)
            throw e
        }
    }
}
