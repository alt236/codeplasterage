package uk.co.alt236.codeplasterage.ext

import java.lang.reflect.AnnotatedElement

object AnnotationExt {

    fun Class<*>.methodsAreAnnotatedWithAnyOf(candidates: Set<Class<out Annotation>>): Boolean {
        for (method in this.declaredMethods) {
            if (method.isAnnotatedWithAnyOf(candidates)) {
                return true
            }
        }
        return false
    }

    fun AnnotatedElement.isAnnotatedWithAnyOf(candidates: Set<Class<out Annotation>>): Boolean {
        for (candidate in candidates) {
            if (this.isAnnotationPresent(candidate)) {
                return true
            }
        }
        return false
    }
}
