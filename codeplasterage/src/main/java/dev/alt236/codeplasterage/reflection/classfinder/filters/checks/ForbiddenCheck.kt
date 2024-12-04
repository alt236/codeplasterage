package dev.alt236.codeplasterage.reflection.classfinder.filters.checks

interface ForbiddenCheck {
    fun isForbidden(clazz: Class<*>): Boolean
}
