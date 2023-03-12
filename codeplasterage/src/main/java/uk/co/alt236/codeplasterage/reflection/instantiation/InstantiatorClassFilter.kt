package uk.co.alt236.codeplasterage.reflection.instantiation

import uk.co.alt236.codeplasterage.CodeplasterageTestRunner
import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.log.Log
import uk.co.alt236.codeplasterage.reflection.classfinder.ClassFinder
import uk.co.alt236.codeplasterage.reflection.methodcalling.MethodCaller

internal class InstantiatorClassFilter {

    fun isExcluded(clazz: Class<*>): Boolean {
        return blackList.contains(clazz.name)
    }

    private companion object {
        private val blackList = setOf(
            ClassFinder::class.java.name,
            Instantiator::class.java.name,
            MethodCaller::class.java.name,
            Log::class.java.name,
            DummyDataFactory::class.java.name,
            CodeplasterageTestRunner::class.java.name
        )
    }
}
