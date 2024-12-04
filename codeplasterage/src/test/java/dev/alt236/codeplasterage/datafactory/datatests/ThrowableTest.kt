package dev.alt236.codeplasterage.datafactory.datatests

import dev.alt236.codeplasterage.datafactory.DataFactoryResult
import dev.alt236.codeplasterage.datafactory.DummyDataFactory
import dev.alt236.codeplasterage.datafactory.stats.DataFactoryRequestRecorder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ThrowableTest {
    private lateinit var sut: DummyDataFactory

    @BeforeEach
    fun setUp() {
        sut = DummyDataFactory(true, DataFactoryRequestRecorder())
    }

    @ParameterizedTest(name = "{index}  =>  type=''{1}'' / value=''{0}''")
    @MethodSource("provide_data")
    fun `returns valid output for input class`(
        input: Any,
        clazz: Class<Any>,
    ) {
        val inputClass = input::class.java
        val result = sut.getDummyDataForType(inputClass)

        assertTrue(result is DataFactoryResult.Valid)
        assertEquals(inputClass, (result as DataFactoryResult.Valid).value!!::class.java)
    }

    companion object {
        @JvmStatic
        fun provide_data(): Stream<Arguments> {
            val args =
                listOf(
                    Throwable(),
                    Exception(),
                    RuntimeException(),
                    Error(),
                    IllegalStateException(),
                    NullPointerException(),
                    IllegalAccessError(),
                )

            return args.map { it.toTestArgs() }.stream()
        }

        private fun Any.toTestArgs(): Arguments = Arguments.of(this, this::class.java)
    }
}
