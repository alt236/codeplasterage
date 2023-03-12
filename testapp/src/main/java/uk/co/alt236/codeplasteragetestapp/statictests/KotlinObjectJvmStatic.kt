@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.statictests

object KotlinObjectJvmStatic {
    @JvmStatic
    fun stringArrayTest(args: Array<String>) {
        args.isEmpty()
    }

    @JvmStatic
    fun intArrayTest(args: Array<Int>) {
        args.isEmpty()
    }

    @JvmStatic
    fun longArrayTest(args: Array<Long>) {
        args.isEmpty()
    }

    @JvmStatic
    fun shortTest(args: Array<Short>) {
        args.isEmpty()
    }

    @JvmStatic
    fun booleanArrayTest(args: Array<Boolean>) {
        args.isEmpty()
    }

    @JvmStatic
    fun byteArrayTest(args: Array<Byte>) {
        args.isEmpty()
    }

    @JvmStatic
    fun charArrayTest(args: Array<Char>) {
        args.isEmpty()
    }

    @JvmStatic
    fun doubleArrayTest(args: Array<Double>) {
        args.isEmpty()
    }
}
