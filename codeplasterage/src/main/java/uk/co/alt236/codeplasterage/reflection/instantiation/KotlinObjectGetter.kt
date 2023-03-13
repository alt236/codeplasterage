package uk.co.alt236.codeplasterage.reflection.instantiation

import uk.co.alt236.codeplasterage.log.Log
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError

class KotlinObjectGetter(private val debug: Boolean) {

    @Suppress("UNCHECKED_CAST")
    fun <T> getKotlinObject(clazz: Class<T>): InstantiatorResult<T>? {
        return try {
            val result = (clazz as Class<*>).kotlin.objectInstance as T?

            return if (result == null) {
                null
            } else {
                printDebug("Is Kotlin object! :$clazz")
                InstantiatorResult.Success(listOf(result), clazz)
            }
        } catch (e: UnsupportedOperationException) {
            Log.logE(e, clazz, "While trying to access Kotlin object instance")
            InstantiatorResult.Error(e, clazz)
        } catch (e: IllegalAccessException) {
            Log.logE(e, clazz, "While trying to access Kotlin object instance")
            InstantiatorResult.Error(e, clazz)
        } catch (e: KotlinReflectionInternalError) {
            Log.logE(e, clazz, "While trying to access Kotlin object instance")
            InstantiatorResult.Error(e, clazz)
        }
    }

    private fun printDebug(message: String) {
        if (debug) {
            Log.log("Instantiator", message)
        }
    }
}