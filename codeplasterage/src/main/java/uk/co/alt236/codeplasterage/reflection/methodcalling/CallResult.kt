package uk.co.alt236.codeplasterage.reflection.methodcalling

sealed class CallResult<T> {
    data class Success<T>(val result: Any?) : CallResult<T>()
    data class Error<T>(val error: Throwable) : CallResult<T>()
}
