package uk.co.alt236.codeplasterage.reflection.classfinder

interface ClassFinderFilter {

    fun isIncluded(clazz: Class<*>): ClassFilterResult
}
