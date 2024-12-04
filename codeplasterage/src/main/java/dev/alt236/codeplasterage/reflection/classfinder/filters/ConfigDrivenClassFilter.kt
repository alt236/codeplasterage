package dev.alt236.codeplasterage.reflection.classfinder.filters

import dev.alt236.codeplasterage.config.TesterConfig

class ConfigDrivenClassFilter(
    config: TesterConfig,
) {
    private val includeClassNamePatterns by lazy {
        val retVal = ArrayList<Regex>()
        for (pattern in config.includeClassNamePatterns) {
            retVal.add(Regex(pattern))
        }
        retVal
    }
    private val excludeClassNamePatterns by lazy {
        val retVal = ArrayList<Regex>()
        for (pattern in config.excludeClassNamePatterns) {
            retVal.add(Regex(pattern))
        }
        retVal
    }

    private val forceIncludeClassNames = config.includeClassNamePatterns
    private val forceExcludeClassNames = config.forceExcludeClassNames

    @Suppress("unused")
    fun isIncluded(clazz: Class<*>): Boolean = isIncluded(clazz.name)

    fun isIncluded(clazzName: String): Boolean {
        if (forceIncludeClassNames.contains(clazzName)) {
            return true
        }

        if (forceExcludeClassNames.contains(clazzName)) {
            return false
        }

        if (includeClassNamePatterns.matches(clazzName) && !excludeClassNamePatterns.matches(clazzName)) {
            return true
        }

        return false
    }

    private fun List<Regex>.matches(input: CharSequence): Boolean {
        for (pattern in this) {
            if (pattern.matches(input)) {
                return true
            }
        }
        return false
    }
}
