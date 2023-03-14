package uk.co.alt236.codeplasterage.datafactory.classtests

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.datafactory.classtests.DummyDataFromClassTest.Companion.InputArgs.Complex
import uk.co.alt236.codeplasterage.datafactory.classtests.DummyDataFromClassTest.Companion.InputArgs.Simple
import uk.co.alt236.codeplasterage.datafactory.stats.DataFactoryRequestRecorder
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.Serializable
import java.util.*
import java.util.stream.Stream

class DummyDataFromClassTest {
    private lateinit var sut: DummyDataFactory

    @BeforeEach
    fun setUp() {
        sut = DummyDataFactory(true, DataFactoryRequestRecorder())
    }

    @ParameterizedTest(name = "{index}  =>  input=''{1}'' / expected=''{0}''")
    @MethodSource("provide_data")
    fun `returns valid output for input class`(inputClass: Class<Any>, expectedClass: Class<Any>) {
        val result = sut.getDummyDataForType(inputClass)

        assertTrue(result is DataFactoryResult.Valid, "Result must be valid, but was: $result")
        (result as DataFactoryResult.Valid) // Smart cast

        assertNotNull(result.value, "Expected value shoud not be null!")
        val actualClass = result.value!!::class.java

        assertAssignable(expectedClass, actualClass)
    }

    private fun assertAssignable(actual: Class<*>, expected: Class<*>) {
        val message = "'$actual' is not a subclass of, or does not implement, '$expected' "
        assertTrue(actual.isAssignableFrom(expected), message)
    }

    companion object {
        @JvmStatic
        fun provide_data(): Stream<Arguments> {
            @Suppress("RemoveRedundantQualifierName")
            val args: List<InputArgs> = listOf(
                Simple(java.lang.String::class.java),
                Simple(String::class.java),

                Simple(java.lang.CharSequence::class.java),
                Simple(kotlin.CharSequence::class.java),

                Simple(java.lang.StringBuffer::class.java),

                Simple(kotlin.text.StringBuilder::class.java),
                Simple(java.lang.StringBuilder::class.java),

                Simple(java.util.Iterator::class.java),
                Simple(kotlin.collections.Iterator::class.java),

                Simple(kotlin.collections.Iterable::class.java),

                Complex(kotlin.collections.Collection::class.java, ArrayList::class.java),
                Complex(java.util.Collection::class.java, ArrayList::class.java),

                Simple(java.util.HashMap::class.java),
                Simple(kotlin.collections.HashMap::class.java),

                Simple(java.util.Map::class.java),
                Simple(kotlin.collections.Map::class.java),
                Simple(kotlin.collections.MutableMap::class.java),

                Simple(java.util.Set::class.java),
                Simple(kotlin.collections.Set::class.java),
                Simple(kotlin.collections.MutableSet::class.java),

                Complex(java.util.List::class.java, java.util.ArrayList::class.java),
                Simple(kotlin.collections.List::class.java),
                Simple(kotlin.collections.MutableList::class.java),

                Simple(java.lang.Object::class.java),
                Simple(Any::class.java),
                Simple(Serializable::class.java),

                Simple(Readable::class.java::class.java),
                Simple(InputStream::class.java),
                Simple(Closeable::class.java),

                Simple(ArithmeticException::class.java),
                Simple(AssertionError::class.java),
                Simple(ClassCastException::class.java),
                Simple(ConcurrentModificationException::class.java),

                Simple(IllegalArgumentException::class.java),
                Simple(IllegalStateException::class.java),
                Simple(IndexOutOfBoundsException::class.java),
                Simple(IOException::class.java),
                Simple(NoSuchElementException::class.java),
                Simple(NullPointerException::class.java),
                Simple(NumberFormatException::class.java),
                Simple(UnsupportedOperationException::class.java),

                Simple(IllegalCallerException::class.java),

                Simple(RuntimeException::class.java),
                Simple(Exception::class.java),
                Simple(Throwable::class.java),
                Simple(Error::class.java)
            )

            return args.map { it.toTestArgs() }.stream()
        }

        private fun InputArgs.toTestArgs(): Arguments {
            return when (this) {
                is Complex -> Arguments.of(this.input, this.expected)
                is Simple -> Arguments.of(this.input, this.input)
            }
        }

        sealed interface InputArgs {
            data class Simple(val input: Class<*>) : InputArgs
            data class Complex(val input: Class<*>, val expected: Class<*>) : InputArgs
        }
    }
}
