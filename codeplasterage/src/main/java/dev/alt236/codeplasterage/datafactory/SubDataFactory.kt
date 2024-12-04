package dev.alt236.codeplasterage.datafactory

import dev.alt236.codeplasterage.log.Log
import java.lang.reflect.Modifier

abstract class SubDataFactory(
    private val debug: Boolean,
) {
    abstract fun canCreateDataFor(clazz: Class<*>): Boolean

    abstract fun getDummyData(clazz: Class<*>): DataFactoryResult<*>

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun logDebug(message: Any) {
        if (debug) {
            Log.log(this::class.java.simpleName, message)
        }
    }

    protected fun <T> tryToInstantiate(clazz: Class<T>): T? {
        logDebug("About to try and force instantiate a '$clazz'")
        val isPossible =
            when {
                Modifier.isPrivate(clazz.modifiers) -> false
                Modifier.isAbstract(clazz.modifiers) -> false
                clazz.isInterface -> false
                clazz.isSynthetic -> false
                clazz.constructors.isEmpty() -> false
                else -> true
            }

        return if (isPossible) {
            try {
                val instance = clazz.getConstructor().newInstance()
                logDebug("Success! '$instance'")
                instance
            } catch (e: Exception) {
                Log.logE(e, clazz, "Trying to instantiate it as a parameter")
                null
            }
        } else {
            logDebug("Class does not seem instantiable... '$clazz'")
            null
        }
    }
}
