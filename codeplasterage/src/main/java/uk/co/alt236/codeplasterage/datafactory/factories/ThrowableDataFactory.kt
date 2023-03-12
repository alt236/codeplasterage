package uk.co.alt236.codeplasterage.datafactory.factories

import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult.Valid
import uk.co.alt236.codeplasterage.datafactory.SubDataFactory
import java.io.IOException
import javax.annotation.Nonnull

class ThrowableDataFactory(debug: Boolean) : SubDataFactory(debug) {

    override fun canCreateDataFor(clazz: Class<*>): Boolean {
        return Throwable::class.java.isAssignableFrom(clazz)
    }

    override fun getDummyData(@Nonnull clazz: Class<*>): DataFactoryResult<*> {
        return when (clazz) {
            ArithmeticException::class.java -> Valid(DUMMY_ARITHMETIC_E, clazz)
            AssertionError::class.java -> Valid(DUMMY_ASSERTION_E, clazz)
            ClassCastException::class.java -> Valid(DUMMY_CLASS_CAST_E, clazz)
            ConcurrentModificationException::class.java -> Valid(DUMMY_CONCURRENT_MODIFICATION_E, clazz)

            IllegalArgumentException::class.java -> Valid(DUMMY_ILLEGAL_ARG_E, clazz)
            IllegalStateException::class.java -> Valid(DUMMY_ILLEGAL_STATE_E, clazz)
            IndexOutOfBoundsException::class.java -> Valid(DUMMY_INDEX_OUT_OF_BOUNDS_E, clazz)
            IOException::class.java -> Valid(DUMMY_IO_E, clazz)
            NoSuchElementException::class.java -> Valid(DUMMY_NO_SUCH_ELEMENT_E, clazz)
            NullPointerException::class.java -> Valid(DUMMY_NULL_POINTER_E, clazz)
            NumberFormatException::class.java -> Valid(DUMMY_NUMBER_FORMAT_E, clazz)
            UnsupportedOperationException::class.java -> Valid(DUMMY_UNSUPPORTED_OP_E, clazz)

            RuntimeException::class.java -> Valid(DUMMY_RUNTIME_E, clazz)
            Exception::class.java -> Valid(DUMMY_EXCEPTION, clazz)
            Throwable::class.java -> Valid(DUMMY_THROWABLE, clazz)
            Error::class.java -> Valid(DUMMY_ERROR, clazz)

            else -> {
                val instantiationAttempt = tryToInstantiate(clazz)
                if (instantiationAttempt == null) {
                    DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
                } else {
                    Valid(instantiationAttempt, clazz)
                }
            }
        }
    }

    private companion object {
        const val MESSAGE_DUMMY_EXCEPTION = "This is a Dummy Exception!"
        const val MESSAGE_DUMMY_ERROR = "This is a Dummy Error!"
        const val MESSAGE_DUMMY_THROWABLE = "This is a Dummy Throwable!"

        val DUMMY_EXCEPTION = Exception(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_RUNTIME_E = RuntimeException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_THROWABLE = Throwable(MESSAGE_DUMMY_THROWABLE)
        val DUMMY_ERROR = Error(MESSAGE_DUMMY_ERROR)

        val DUMMY_ARITHMETIC_E = ArithmeticException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_ASSERTION_E = AssertionError(MESSAGE_DUMMY_ERROR)
        val DUMMY_CLASS_CAST_E = ClassCastException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_CONCURRENT_MODIFICATION_E = ConcurrentModificationException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_ILLEGAL_ARG_E = IllegalArgumentException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_ILLEGAL_STATE_E = IllegalStateException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_INDEX_OUT_OF_BOUNDS_E = IndexOutOfBoundsException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_IO_E = IOException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_NO_SUCH_ELEMENT_E = NoSuchElementException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_NULL_POINTER_E = NullPointerException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_NUMBER_FORMAT_E = NumberFormatException(MESSAGE_DUMMY_EXCEPTION)
        val DUMMY_UNSUPPORTED_OP_E = UnsupportedOperationException(MESSAGE_DUMMY_EXCEPTION)
    }
}
