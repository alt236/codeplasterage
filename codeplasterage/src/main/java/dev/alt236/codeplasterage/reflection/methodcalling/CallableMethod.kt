package dev.alt236.codeplasterage.reflection.methodcalling

import java.lang.reflect.Method

sealed interface CallableMethod<T> {
    val clazz: Class<T>
    val method: Method

    data class InstanceMethod<T>(
        override val clazz: Class<T>,
        override val method: Method,
    ) : CallableMethod<T>

    data class StaticMethod<T>(
        override val clazz: Class<T>,
        override val method: Method,
    ) : CallableMethod<T>
}
