package dev.alt236.codeplasteragetestapp.classparsing.arraytests

import java.io.Serializable

interface CommonArrayTests {
    fun booleanArrayTest(args: Array<Boolean>)

    fun byteArrayTest(args: Array<Byte>)

    fun charArrayTest(args: Array<Char>)

    fun doubleArrayTest(args: Array<Double>)

    fun intArrayTest(args: Array<Int>)

    fun longArrayTest(args: Array<Long>)

    fun shortTest(args: Array<Short>)

    fun stringArrayTest(args: Array<String>)

    fun serializableArray(args: Array<Serializable>)
}
