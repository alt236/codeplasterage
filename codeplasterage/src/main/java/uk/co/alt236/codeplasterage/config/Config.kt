@file:Suppress("unused")

package uk.co.alt236.codeplasterage.config

import uk.co.alt236.codeplasterage.config.DefaultConfigValues.DEBUG
import uk.co.alt236.codeplasterage.config.DefaultConfigValues.IGNORE_ERRORS

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Config(
    val includeClassNamePatterns: Array<String> = [],
    val excludeClassNamePatterns: Array<String> = [],
    val forceIncludeClassNames: Array<String> = [],
    val forceExcludeClassNames: Array<String> = [],
    val ignoreErrors: Boolean = IGNORE_ERRORS,
    val debug: Boolean = DEBUG
)

internal object DefaultConfigValues {
    const val IGNORE_ERRORS = true
    const val DEBUG = false
    val DEFAULT_INCLUDE_CLASS_NAME_PATTERNS = emptyArray<String>()
    val DEFAULT_EXCLUDE_CLASS_NAME_PATTERNS = emptyArray<String>()
    val DEFAULT_FORCE_INCLUDE_CLASS_NAMES = emptyArray<String>()
    val DEFAULT_FORCE_EXCLUDE_CLASS_NAMES = emptyArray<String>()
}
