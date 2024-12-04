package dev.alt236.codeplasteragetestapp.libcomponents

import dev.alt236.codeplasterage.datafactory.DataFactoryResult
import dev.alt236.codeplasterage.datafactory.SubDataFactory
import dev.alt236.codeplasteragetestapp.dagger.TestDaggerInjectable

class TestDummyDataFactory(
    debug: Boolean,
) : SubDataFactory(debug) {
    override fun canCreateDataFor(clazz: Class<*>): Boolean = clazz == TestDaggerInjectable::class.java

    override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> =
        when (clazz) {
            TestDaggerInjectable::class.java -> DataFactoryResult.Valid(TestDaggerInjectable(), clazz)
            else -> DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
        }
}
