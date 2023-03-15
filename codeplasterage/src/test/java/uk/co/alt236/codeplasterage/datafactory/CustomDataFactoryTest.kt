package uk.co.alt236.codeplasterage.datafactory

import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import uk.co.alt236.codeplasterage.datafactory.stats.DataFactoryRequestRecorder

class CustomDataFactoryTest {

    @Test
    fun `DummyDataFactory does not provide TestClass by default`() {
        val dataFactory = DummyDataFactory(true, DataFactoryRequestRecorder())
        val result = dataFactory.getDummyDataForType(BrandNewClass::class.java)
        assertInstanceOf(DataFactoryResult.Valid::class.java, result)
        assertNull((result as DataFactoryResult.Valid).value)
    }

    @Test
    fun `DummyDataFactory provides TestClass if correct factory has been added`() {
        val customFactory = BrandNewClassDataFactory(true)
        val dataFactory = DummyDataFactory(true, DataFactoryRequestRecorder(), listOf(customFactory))

        // Check custom class
        val result = dataFactory.getDummyDataForType(BrandNewClass::class.java)
        assertInstanceOf(DataFactoryResult.Valid::class.java, result)
        assertInstanceOf(BrandNewClass::class.java, (result as DataFactoryResult.Valid).value)

        // Test that we haven't broken anything else
        val result2 = dataFactory.getDummyDataForType(String::class.java)
        assertInstanceOf(DataFactoryResult.Valid::class.java, result2)
        assertInstanceOf(String::class.java, (result2 as DataFactoryResult.Valid).value)
    }

    private class BrandNewClass

    private class BrandNewClassDataFactory(debug: Boolean) : SubDataFactory(debug) {
        override fun canCreateDataFor(clazz: Class<*>): Boolean {
            return clazz == BrandNewClass::class.java
        }

        override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
            return when (clazz) {
                BrandNewClass::class.java -> DataFactoryResult.Valid(BrandNewClass(), clazz)
                else -> DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
            }
        }
    }
}
