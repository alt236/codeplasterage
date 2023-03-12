package uk.co.alt236.codeplasterage.reflection.methodcalling

import uk.co.alt236.codeplasterage.log.Log
import java.lang.reflect.Method
import java.lang.reflect.Modifier

class MethodFinder(val debug: Boolean) {
    fun <T> getCallableMethods(clazz: Class<T>): List<CallableMethod<T>> {
        val methods = clazz.declaredMethods ?: emptyArray()
        val filtered = methods.toList()
            .filterUnusable()
            .map { map(clazz, it) }
            .sortedBy { it.method.toString() }

        printDebug("Class: $clazz --- Methods: ${filtered.size}/${methods.size}")
        return filtered
    }

    private fun <T> map(clazz: Class<T>, method: Method): CallableMethod<T> {
        return if (Modifier.isStatic(method.modifiers)) {
            CallableMethod.StaticMethod(clazz, method)
        } else {
            CallableMethod.InstanceMethod(clazz, method)
        }
    }

    private fun List<Method>.filterUnusable(): List<Method> {
        val valid = ArrayList<Method>(this.size)
        for (method in this) {
            if (Modifier.isNative(method.modifiers)) {
                continue
            }

            if (Modifier.isPrivate(method.modifiers)) {
                continue
            }

            valid.add(method)
        }

        return valid
    }

    private fun printDebug(message: String) {
        if (debug) {
            Log.log("MethodFinder", message)
        }
    }
}
