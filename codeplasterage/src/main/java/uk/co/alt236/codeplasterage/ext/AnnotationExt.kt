package uk.co.alt236.codeplasterage.ext

import java.lang.reflect.AnnotatedElement

object AnnotationExt {

    fun Class<*>.methodsAreAnnotatedWithAnyOf(candidates: Set<NormalisedClassName>): Boolean {
        for (method in this.declaredMethods) {
            if (method.isAnnotatedWithAnyOf(candidates)) {
                return true
            }
        }
        return false
    }

    fun AnnotatedElement.isAnnotatedWithAnyOf(candidates: Set<NormalisedClassName>): Boolean {
        return when {
            declaredAnnotations.isEmpty() -> false
            candidates.isEmpty() -> false
            else -> {
                val normalisedAnnotations = this.getNormalisedNameAnnotations()
                return normalisedAnnotations.containsAnyOf(candidates)
            }
        }
    }

    private fun AnnotatedElement.getNormalisedNameAnnotations(): Set<NormalisedClassName> {
        val annotations = this.declaredAnnotations
        return annotations.map { it.annotationClass.java }.toNormalisedClassNameSet()
    }
}
