package uk.co.alt236.codeplasterage.ext

import org.apache.commons.lang3.ClassUtils
import uk.co.alt236.codeplasterage.log.Log

object ClassExt {

    fun Class<*>.canLoadMethods(): Boolean {
        try {
            this.methods
            return true
        } catch (e: Throwable) {
            Log.logE(e, this)
        }
        return false
    }

    fun Class<*>.implementsOrIsAnyOf(interfaces: Set<Class<*>>): Boolean {
        val set = this.getAllInterfaces().toMutableSet()

        if (this.isInterface) {
            set.add(this)
        }

        for (candidate in interfaces) {
            if (set.contains(candidate)) {
                return true
            }
        }
        return false
    }

    fun Class<*>.isAssignableToAnyOf(candidates: Set<NormalisedClassName>): Boolean {
        val thisSupers = (this.getAllInterfaces() + this.getAllSuperClasses()).toNormalisedClassNameSet()
        return thisSupers.containsAnyOf(candidates)
    }

    fun Class<*>.getAllInterfaces(): Set<Class<*>> {
        return ClassUtils.getAllInterfaces(this).toSet()
    }

    fun Class<*>.getAllSuperClasses(): Set<Class<*>> {
        return ClassUtils.getAllSuperclasses(this).toSet()
    }
}
