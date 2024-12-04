package dev.alt236.codeplasterage.reflection.classfinder.filters.checks

import dev.alt236.codeplasterage.ext.AnnotationExt.methodsAreAnnotatedWithAnyOf
import dev.alt236.codeplasterage.ext.NormalisedClassName

class CheckForbiddenMethodAnnotations : ForbiddenCheck {
    override fun isForbidden(clazz: Class<*>): Boolean = clazz.methodsAreAnnotatedWithAnyOf(FORBIDDEN_METHOD_ANNOTATIONS)

    companion object {
        private val FORBIDDEN_METHOD_ANNOTATIONS =
            setOf(
                NormalisedClassName("org.junit.jupiter.api.Test"),
                NormalisedClassName(org.junit.Test::class.java),
            )
    }
}
