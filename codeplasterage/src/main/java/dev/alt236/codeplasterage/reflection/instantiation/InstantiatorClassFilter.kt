package dev.alt236.codeplasterage.reflection.instantiation

import dev.alt236.codeplasterage.CodeplasterageTestRunner
import dev.alt236.codeplasterage.datafactory.DummyDataFactory
import dev.alt236.codeplasterage.log.Log
import dev.alt236.codeplasterage.reflection.classfinder.ClassFinder
import dev.alt236.codeplasterage.reflection.methodcalling.MethodCaller

internal class InstantiatorClassFilter {
    fun isExcluded(clazz: Class<*>): Boolean = blackList.contains(clazz.name)

    private companion object {
        private val blackList =
            setOf(
                ClassFinder::class.java.name,
                Instantiator::class.java.name,
                MethodCaller::class.java.name,
                Log::class.java.name,
                DummyDataFactory::class.java.name,
                CodeplasterageTestRunner::class.java.name,
            )
    }
}
