@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.arraytests

import java.io.Serializable

class KotlinClass : CommonArrayTests {
    override fun booleanArrayTest(args: Array<Boolean>) {
        args.isEmpty()
    }

    override fun byteArrayTest(args: Array<Byte>) {
        args.isEmpty()
    }

    override fun charArrayTest(args: Array<Char>) {
        args.isEmpty()
    }

    override fun doubleArrayTest(args: Array<Double>) {
        args.isEmpty()
    }

    override fun intArrayTest(args: Array<Int>) {
        args.isEmpty()
    }

    override fun longArrayTest(args: Array<Long>) {
        args.isEmpty()
    }

    override fun shortTest(args: Array<Short>) {
        args.isEmpty()
    }

    override fun stringArrayTest(args: Array<String>) {
        args.isEmpty()
    }

    override fun serializableArray(args: Array<Serializable>) {
        args.isEmpty()
    }
}
