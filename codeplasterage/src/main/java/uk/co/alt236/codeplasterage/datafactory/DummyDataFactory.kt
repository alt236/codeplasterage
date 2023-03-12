@file:Suppress("MemberVisibilityCanBePrivate")

package uk.co.alt236.codeplasterage.datafactory

import uk.co.alt236.codeplasterage.datafactory.factories.ArrayFactory
import uk.co.alt236.codeplasterage.datafactory.factories.CollectionDataFactory
import uk.co.alt236.codeplasterage.datafactory.factories.ObjectDataFactory
import uk.co.alt236.codeplasterage.datafactory.factories.PrimitiveDataFactory
import uk.co.alt236.codeplasterage.datafactory.factories.ThrowableDataFactory
import uk.co.alt236.codeplasterage.log.Log
import java.lang.reflect.Executable

private const val LOG_TAG = "DummyDataFactory"

internal class DummyDataFactory(private val debug: Boolean) {

    private val factories by lazy {
        val primitiveFactory = PrimitiveDataFactory(debug)

        listOf(
            primitiveFactory,
            ArrayFactory(debug),
            CollectionDataFactory(debug),
            ThrowableDataFactory(debug),
            ObjectDataFactory(debug, primitiveFactory)
        )
    }

    fun getDummyDataToCall(executable: Executable): List<Any?>? {
        val retVal = ArrayList<Any?>()

        for (param in executable.parameterTypes) {
            val data = getDummyDataForType(param)

            if (data is DataFactoryResult.Valid<*>) {
                retVal.add(data.value)
            } else {
                return null
            }
        }

        return retVal
    }

    fun getDummyDataForType(clazz: Class<*>): DataFactoryResult<*> {
        for (factory in factories) {
            if (factory.canCreateDataFor(clazz)) {
                when (val data = factory.getDummyData(clazz)) {
                    is DataFactoryResult.Error -> {
                        printDebug("Factory failed: ${data.t.message}")
                    }

                    is DataFactoryResult.Valid -> {
                        printDebug("Factory Success!: [${factory::class.java.simpleName}] produced '${data.value} for '$clazz'")
                        return data
                    }
                }
            }
        }

        printDebug("!!! Don't know how to produce dummy data for: '$clazz' - will return null!")
        return DataFactoryResult.Valid(null, clazz)
    }

    private fun printDebug(message: String) {
        if (debug) {
            Log.log(LOG_TAG, message)
        }
    }
}
