@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.nested

import uk.co.alt236.codeplasteragetestapp.TestInputLogger
import uk.co.alt236.codeplasteragetestapp.arraytests.CommonArrayTests
import java.io.Serializable

class KotlinCompanionObject {

    object Foo : CommonArrayTests {
        override fun stringArrayTest(args: Array<String>) {
            TestInputLogger.log(args)
        }

        override fun serializableArray(args: Array<Serializable>) {
            TestInputLogger.log(args)
        }

        override fun intArrayTest(args: Array<Int>) {
            TestInputLogger.log(args)
        }

        override fun longArrayTest(args: Array<Long>) {
            TestInputLogger.log(args)
        }

        override fun shortTest(args: Array<Short>) {
            TestInputLogger.log(args)
        }

        override fun booleanArrayTest(args: Array<Boolean>) {
            TestInputLogger.log(args)
        }

        override fun byteArrayTest(args: Array<Byte>) {
            TestInputLogger.log(args)
        }

        override fun charArrayTest(args: Array<Char>) {
            TestInputLogger.log(args)
        }

        override fun doubleArrayTest(args: Array<Double>) {
            TestInputLogger.log(args)
        }
    }

    companion object : CommonArrayTests {
        override fun stringArrayTest(args: Array<String>) {
            TestInputLogger.log(args)
        }

        override fun serializableArray(args: Array<Serializable>) {
            TestInputLogger.log(args)
        }

        override fun intArrayTest(args: Array<Int>) {
            TestInputLogger.log(args)
        }

        override fun longArrayTest(args: Array<Long>) {
            TestInputLogger.log(args)
        }

        override fun shortTest(args: Array<Short>) {
            TestInputLogger.log(args)
        }

        override fun booleanArrayTest(args: Array<Boolean>) {
            TestInputLogger.log(args)
        }

        override fun byteArrayTest(args: Array<Byte>) {
            TestInputLogger.log(args)
        }

        override fun charArrayTest(args: Array<Char>) {
            TestInputLogger.log(args)
        }

        override fun doubleArrayTest(args: Array<Double>) {
            TestInputLogger.log(args)
        }

        object CompanionInner : CommonArrayTests {
            override fun stringArrayTest(args: Array<String>) {
                TestInputLogger.log(args)
            }

            override fun serializableArray(args: Array<Serializable>) {
                TestInputLogger.log(args)
            }

            override fun intArrayTest(args: Array<Int>) {
                TestInputLogger.log(args)
            }

            override fun longArrayTest(args: Array<Long>) {
                TestInputLogger.log(args)
            }

            override fun shortTest(args: Array<Short>) {
                TestInputLogger.log(args)
            }

            override fun booleanArrayTest(args: Array<Boolean>) {
                TestInputLogger.log(args)
            }

            override fun byteArrayTest(args: Array<Byte>) {
                TestInputLogger.log(args)
            }

            override fun charArrayTest(args: Array<Char>) {
                TestInputLogger.log(args)
            }

            override fun doubleArrayTest(args: Array<Double>) {
                TestInputLogger.log(args)
            }
        }
    }
}