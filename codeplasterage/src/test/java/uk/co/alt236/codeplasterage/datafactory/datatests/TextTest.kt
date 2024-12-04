package uk.co.alt236.codeplasterage.datafactory.datatests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.datafactory.stats.DataFactoryRequestRecorder
import java.util.stream.Stream

internal class TextTest {
    private lateinit var sut: DummyDataFactory

    @BeforeEach
    fun setUp() {
        sut = DummyDataFactory(true, DataFactoryRequestRecorder())
    }

    @ParameterizedTest(name = "{index}  =>  type=''{1}'' / value=''{0}''")
    @MethodSource("provide_data")
    fun `returns valid output for input class`(input: Any, clazz: Class<Any>) {
        val inputClass = input::class.java
        val result = sut.getDummyDataForType(inputClass)

        assertTrue(result is DataFactoryResult.Valid)
        assertEquals(inputClass, (result as DataFactoryResult.Valid).value!!::class.java)
    }

    companion object {
        @JvmStatic
        fun provide_data(): Stream<Arguments> {
            val args = listOf(
                "Foo",
                "Foo" as CharSequence,
                StringBuffer(),
                StringBuilder()
            )

            return args.map { it.toTestArgs() }.stream()
        }

        private fun Any.toTestArgs(): Arguments {
            return Arguments.of(this, this::class.java)
        }
    }
}
