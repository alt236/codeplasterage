package uk.co.alt236.codeplasterage.config

import java.lang.reflect.Method
import kotlin.reflect.KClass

internal class ConfigFactory {

    fun getConfig(testClass: Class<*>): TesterConfig {
        val classAnnotation = testClass.getDeclaredAnnotation(Config::class.java)
        return compileConfig(null, classAnnotation)
    }

    fun compileConfig(testClass: Class<*>, testMethod: Method): TesterConfig {
        val classAnnotation = testClass.getDeclaredAnnotation(Config::class.java)
        val methodAnnotation = testMethod.getDeclaredAnnotation(Config::class.java)

        return compileConfig(methodAnnotation, classAnnotation)
    }

    private fun compileConfig(methodAnnotation: Config?, classAnnotation: Config?): TesterConfig {
        return TesterConfig(
            ignoreErrors = getIgnoreErrors(methodAnnotation, classAnnotation),
            debug = getDebug(methodAnnotation, classAnnotation),
            includeClassNamePatterns = getPrioritizedSet(
                methodAnnotation?.includeClassNamePatterns,
                classAnnotation?.includeClassNamePatterns,
                DefaultConfigValues.DEFAULT_INCLUDE_CLASS_NAME_PATTERNS
            ),
            excludeClassNamePatterns = getPrioritizedSet(
                methodAnnotation?.excludeClassNamePatterns,
                classAnnotation?.excludeClassNamePatterns,
                DefaultConfigValues.DEFAULT_EXCLUDE_CLASS_NAME_PATTERNS
            ),
            forceIncludeClassNames = getPrioritizedSet(
                methodAnnotation?.forceIncludeClassNames,
                classAnnotation?.forceIncludeClassNames,
                DefaultConfigValues.DEFAULT_FORCE_INCLUDE_CLASS_NAMES
            ),
            forceExcludeClassNames = getPrioritizedSet(
                methodAnnotation?.forceExcludeClassNames,
                classAnnotation?.forceExcludeClassNames,
                DefaultConfigValues.DEFAULT_FORCE_EXCLUDE_CLASS_NAMES
            ),
            customDummyDataFactories = getPrioritizedList(
                methodAnnotation?.customDummyDataFactories,
                classAnnotation?.customDummyDataFactories,
                emptyArray()
            )
        )
    }

    private fun <T : Any> getPrioritizedList(
        methodArray: Array<KClass<T>>?,
        classArray: Array<KClass<T>>?,
        fallback: Array<KClass<T>>
    ): List<KClass<T>> {
        return when {
            methodArray != null -> methodArray
            classArray != null -> classArray
            else -> fallback
        }.toList()
    }

    private fun getPrioritizedSet(
        methodArray: Array<String>?,
        classArray: Array<String>?,
        fallback: Array<String>
    ): Set<String> {
        return when {
            methodArray != null -> methodArray
            classArray != null -> classArray
            else -> fallback
        }.toSet()
    }

    private fun getIgnoreErrors(
        methodAnnotation: Config?,
        classAnnotation: Config?
    ): Boolean {
        return when {
            methodAnnotation != null -> methodAnnotation.ignoreErrors
            classAnnotation != null -> classAnnotation.ignoreErrors
            else -> DefaultConfigValues.IGNORE_ERRORS
        }
    }

    private fun getDebug(
        methodAnnotation: Config?,
        classAnnotation: Config?
    ): Boolean {
        return when {
            methodAnnotation != null -> methodAnnotation.debug
            classAnnotation != null -> classAnnotation.debug
            else -> DefaultConfigValues.DEBUG
        }
    }
}
