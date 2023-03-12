package uk.co.alt236.codeplasterage.config

data class TesterConfig(
    val includeClassNamePatterns: Set<String>,
    val excludeClassNamePatterns: Set<String>,
    val forceIncludeClassNames: Set<String>,
    val forceExcludeClassNames: Set<String>,
    val ignoreErrors: Boolean,
    val debug: Boolean
)
