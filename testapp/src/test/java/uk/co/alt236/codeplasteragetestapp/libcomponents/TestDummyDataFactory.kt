package uk.co.alt236.codeplasteragetestapp.libcomponents

import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.SubDataFactory
import uk.co.alt236.codeplasteragetestapp.dagger.TestDaggerInjectable

class TestDummyDataFactory(debug: Boolean) : SubDataFactory(debug) {
    override fun canCreateDataFor(clazz: Class<*>): Boolean {
        return clazz == TestDaggerInjectable::class.java
    }

    override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
        return when (clazz) {
            TestDaggerInjectable::class.java -> DataFactoryResult.Valid(TestDaggerInjectable(), clazz)
            else -> DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
        }
    }
}
