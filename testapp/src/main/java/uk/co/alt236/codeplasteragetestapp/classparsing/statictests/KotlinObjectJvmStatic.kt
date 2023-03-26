@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.classparsing.statictests

import uk.co.alt236.codeplasteragetestapp.TestInputLogger

object KotlinObjectJvmStatic {
    @JvmStatic
    fun stringArrayTest(args: Array<String>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun intArrayTest(args: Array<Int>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun longArrayTest(args: Array<Long>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun shortTest(args: Array<Short>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun booleanArrayTest(args: Array<Boolean>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun byteArrayTest(args: Array<Byte>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun charArrayTest(args: Array<Char>) {
        TestInputLogger.log(args)
    }

    @JvmStatic
    fun doubleArrayTest(args: Array<Double>) {
        TestInputLogger.log(args)
    }
}
