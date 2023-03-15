package uk.co.alt236.codeplasterage.datafactory

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CustomDataFactoryFactoryTest {

    private lateinit var sut: CustomDataFactoryFactory

    @BeforeEach
    fun setUp() {
        sut = CustomDataFactoryFactory(debug = true)
    }

    @Test
    fun `CustomDataFactoryFactory throws if asked to instantiate class with empty constructor`() {
        val input = listOf(FactoryWithWrongConstructor_Empty::class)
        assertThrows<NoSuchMethodException>("Should have thrown") { sut.createSubDataFactories(input) }
    }

    @Test
    fun `CustomDataFactoryFactory throws if asked to instantiate class with wrong param constructor`() {
        val input = listOf(FactoryWithWrongConstructor_NonBoolean::class)
        assertThrows<NoSuchMethodException>("Should have thrown") { sut.createSubDataFactories(input) }
    }

    @Test
    fun `CustomDataFactoryFactory throws if asked to instantiate class with wrong multiple param constructor`() {
        val input = listOf(FactoryWithWrongConstructor_MultipleParams::class)
        assertThrows<NoSuchMethodException>("Should have thrown") { sut.createSubDataFactories(input) }
    }

    @Test
    fun `CustomDataFactoryFactory correctly creates custom Factory if class constructor is valid`() {
        val input = listOf(FactoryWithCorrectConstructor::class)
        val result = sut.createSubDataFactories(input)
        assertEquals(1, result.size)
        assertInstanceOf(FactoryWithCorrectConstructor::class.java, result.first())
    }

    @Suppress("unused")
    private class FactoryWithCorrectConstructor(val boolean: Boolean) : SubDataFactory(true) {
        override fun canCreateDataFor(clazz: Class<*>): Boolean {
            return true
        }

        override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
            return DataFactoryResult.Error(IllegalStateException(), clazz)
        }
    }

    @Suppress("ClassName")
    private class FactoryWithWrongConstructor_Empty : SubDataFactory(true) {
        override fun canCreateDataFor(clazz: Class<*>): Boolean {
            return true
        }

        override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
            return DataFactoryResult.Error(IllegalStateException(), clazz)
        }
    }

    @Suppress("ClassName", "unused")
    private class FactoryWithWrongConstructor_NonBoolean(private val foo: String) : SubDataFactory(true) {
        override fun canCreateDataFor(clazz: Class<*>): Boolean {
            return true
        }

        override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
            return DataFactoryResult.Error(IllegalStateException(), clazz)
        }
    }

    @Suppress("ClassName", "unused")
    private class FactoryWithWrongConstructor_MultipleParams(
        boolean: Boolean,
        private val foo: String
    ) : SubDataFactory(boolean) {
        override fun canCreateDataFor(clazz: Class<*>): Boolean {
            return true
        }

        override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
            return DataFactoryResult.Error(IllegalStateException(), clazz)
        }
    }
}
