package uk.co.alt236.codeplasterage.datafactory

sealed class DataFactoryResult<T> {
    data class Valid<T>(val value: T?, val inputClass: Class<*>) : DataFactoryResult<T>()
    data class Error<T>(val t: Throwable, val clazz: Class<T>) : DataFactoryResult<T>()

    companion object {
        fun <T> createUnableToCreateInstanceError(factory: SubDataFactory, clazz: Class<T>): Error<T> {
            val message = "${factory::class.java.simpleName}: Don't know how to produce dummy data for: '$clazz'"
            return Error(IllegalStateException(message), clazz)
        }
    }
}
