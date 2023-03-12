package uk.co.alt236.codeplasterage.reflection.classfinder.filters

import org.junit.Test
import org.junit.runner.RunWith
import uk.co.alt236.codeplasterage.Consts
import uk.co.alt236.codeplasterage.ext.AnnotationExt.isAnnotatedWithAnyOf
import uk.co.alt236.codeplasterage.ext.AnnotationExt.methodsAreAnnotatedWithAnyOf
import uk.co.alt236.codeplasterage.ext.ClassExt.canLoadMethods
import uk.co.alt236.codeplasterage.reflection.classfinder.ClassFinderFilter
import java.lang.reflect.Modifier

class SystemClassFilter : ClassFinderFilter {

    override fun isIncluded(clazz: Class<*>): Boolean {
        return when {
            Modifier.isPrivate(clazz.modifiers) -> false
            clazz.isAnnotation -> false
            clazz.isInterface -> false
            Modifier.isAbstract(clazz.modifiers) -> false

            clazz.`package`.name == Consts.ROOT_PACKAGE -> false // We don't want any weird test loops
            clazz.`package`.name.startsWith(Consts.ROOT_PACKAGE + ".") -> false // We don't want any weird test loops

            clazz.name.startsWith("_") -> false
            clazz.name.startsWith("Hilt_") -> false
            clazz.name.endsWith("_GeneratedInjector") -> false
            clazz.name.endsWith("_MembersInjector") -> false
            clazz.name.endsWith("_Factory") -> false
            clazz.name.endsWith("_HiltModules") -> false

            !clazz.canLoadMethods() -> false
            clazz.isAnnotatedWithAnyOf(FORBIDDEN_CLASS_ANNOTATIONS) -> false
            clazz.methodsAreAnnotatedWithAnyOf(FORBIDDEN_METHOD_ANNOTATIONS) -> false // skip any tests
            else -> return true
        }
    }

    private companion object {
        private val FORBIDDEN_CLASS_ANNOTATIONS = setOf<Class<out Annotation>>(
            RunWith::class.java
        )

        private val FORBIDDEN_METHOD_ANNOTATIONS = setOf<Class<out Annotation>>(
            Test::class.java
        )
    }
}
