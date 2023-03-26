@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.classparsing.primitivetests

import uk.co.alt236.codeplasteragetestapp.TestInputLogger

class KotlinClass : CommonPrimitiveTests {
    override fun stringTest(arg: String) {
        TestInputLogger.log(arg)
    }

    override fun booleanTest(arg: Boolean) {
        TestInputLogger.log(arg)
    }

    override fun intTest(arg: Int) {
        TestInputLogger.log(arg)
    }

    override fun longTest(arg: Long) {
        TestInputLogger.log(arg)
    }

    override fun shortTest(arg: Short) {
        TestInputLogger.log(arg)
    }

    override fun byteTest(arg: Byte) {
        TestInputLogger.log(arg)
    }

    override fun charTest(arg: Char) {
        TestInputLogger.log(arg)
    }

    override fun doubleTest(arg: Double) {
        TestInputLogger.log(arg)
    }

    override fun floatTest(arg: Float) {
        TestInputLogger.log(arg)
    }
}
