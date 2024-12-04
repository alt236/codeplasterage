package dev.alt236.codeplasterage.datafactory.factories

import dev.alt236.codeplasterage.datafactory.DataFactoryResult
import dev.alt236.codeplasterage.datafactory.DataFactoryResult.Valid
import dev.alt236.codeplasterage.datafactory.SubDataFactory
import java.lang.reflect.Array

class ArrayFactory(
    debug: Boolean,
) : SubDataFactory(debug) {
    override fun canCreateDataFor(clazz: Class<*>) = clazz.isArray

    override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
        val array = Array.newInstance(clazz.componentType, 0)
        return Valid(array, clazz)
    }
}
