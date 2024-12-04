package dev.alt236.codeplasterage.ext

data class NormalisedClassName(
    val className: String,
) {
    constructor(clazz: Class<*>) : this(clazz.name)
}

fun Array<Class<*>>.toNormalisedClassNameSet(): Set<NormalisedClassName> = this.map { NormalisedClassName(it) }.toSet()

fun Collection<Class<*>>.toNormalisedClassNameSet(): Set<NormalisedClassName> = this.map { NormalisedClassName(it) }.toSet()

internal fun Set<NormalisedClassName>.containsAnyOf(other: Set<NormalisedClassName>): Boolean {
    for (candidate in other) {
        if (this.contains(candidate)) {
            return true
        }
    }

    return false
}
