@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.nested

import uk.co.alt236.codeplasteragetestapp.arraytests.CommonArrayTests
import java.io.Serializable

class KotlinCompanionObject {

    object Foo : CommonArrayTests {
        override fun stringArrayTest(args: Array<String>) {
            args.isEmpty()
        }

        override fun serializableArray(args: Array<Serializable>) {
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
    }

    companion object : CommonArrayTests {
        override fun stringArrayTest(args: Array<String>) {
            args.isEmpty()
        }

        override fun serializableArray(args: Array<Serializable>) {
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

        object CompanionInner : CommonArrayTests {
            override fun stringArrayTest(args: Array<String>) {
                args.isEmpty()
            }

            override fun serializableArray(args: Array<Serializable>) {
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
        }
    }
}
