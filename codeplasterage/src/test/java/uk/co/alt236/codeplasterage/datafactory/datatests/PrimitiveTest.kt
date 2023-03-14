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
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.stream.Stream

internal class PrimitiveTest {
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
                1,
                1L,
                'c',
                true,
                false,
                1.1,
                1.1F,
                Date(),
                BigDecimal(1),
                BigInteger("2"),
                Object::class.java,
                Object(),
                Locale.CANADA,
            )

            return args.map { it.toTestArgs() }.stream()
        }

        private fun Any.toTestArgs(): Arguments {
            return Arguments.of(this, this::class.java)
        }
    }
}
