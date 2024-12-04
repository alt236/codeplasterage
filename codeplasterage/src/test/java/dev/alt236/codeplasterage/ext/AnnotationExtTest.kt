package dev.alt236.codeplasterage.ext

import dev.alt236.codeplasterage.ext.AnnotationExt.methodsAreAnnotatedWithAnyOf
import dev.alt236.codeplasterage.testdata.ClassWithJunitTestMethodAnnotation
import dev.alt236.codeplasterage.testdata.ClassWithJupiterTestMethodAnnotation
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AnnotationExtTest {
    @Test
    fun `methodsAreAnnotatedWithAnyOf - finds annotated method - junit`() {
        val clazz = ClassWithJunitTestMethodAnnotation::class.java
        val result = clazz.methodsAreAnnotatedWithAnyOf(FORBIDDEN_ANNOTATIONS)
        assertTrue(result)
    }

    @Test
    fun `methodsAreAnnotatedWithAnyOf - finds annotated method - jupiter`() {
        val clazz = ClassWithJupiterTestMethodAnnotation::class.java
        val result = clazz.methodsAreAnnotatedWithAnyOf(FORBIDDEN_ANNOTATIONS)
        assertTrue(result)
    }

    private companion object {
        val FORBIDDEN_ANNOTATIONS =
            setOf(
                NormalisedClassName(org.junit.Test::class.java),
                NormalisedClassName(Test::class.java),
            )
    }
}
