package dev.alt236.codeplasterage.reflection.instantiation

sealed class InstantiatorResult<T> {
    data class Success<T>(
        val instances: List<T>,
        val clazz: Class<T>,
    ) : InstantiatorResult<T>()

    data class Error<T>(
        val error: Throwable,
        val clazz: Class<T>,
    ) : InstantiatorResult<T>()

    data class Ignored<T>(
        val reason: String,
        val clazz: Class<T>,
    ) : InstantiatorResult<T>()
}
