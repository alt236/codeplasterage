package uk.co.alt236.codeplasterage.config

import uk.co.alt236.codeplasterage.datafactory.SubDataFactory
import kotlin.reflect.KClass

data class TesterConfig(
    val includeClassNamePatterns: Set<String>,
    val excludeClassNamePatterns: Set<String>,
    val forceIncludeClassNames: Set<String>,
    val forceExcludeClassNames: Set<String>,
    val customDummyDataFactories: List<KClass<SubDataFactory>>,
    val ignoreErrors: Boolean,
    val debug: Boolean
)
