@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.statictests

import uk.co.alt236.codeplasteragetestapp.TestInputLogger

object KotlinObject {
    fun stringArrayTest(args: Array<String>) {
        TestInputLogger.log(args)
    }

    fun intArrayTest(args: Array<Int>) {
        TestInputLogger.log(args)
    }

    fun longArrayTest(args: Array<Long>) {
        TestInputLogger.log(args)
    }

    fun shortTest(args: Array<Short>) {
        TestInputLogger.log(args)
    }

    fun booleanArrayTest(args: Array<Boolean>) {
        TestInputLogger.log(args)
    }

    fun byteArrayTest(args: Array<Byte>) {
        TestInputLogger.log(args)
    }

    fun charArrayTest(args: Array<Char>) {
        TestInputLogger.log(args)
    }

    fun doubleArrayTest(args: Array<Double>) {
        TestInputLogger.log(args)
    }
}
