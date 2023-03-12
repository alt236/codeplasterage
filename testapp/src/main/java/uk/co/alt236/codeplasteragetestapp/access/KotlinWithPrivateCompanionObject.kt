@file:Suppress("unused")

package uk.co.alt236.codeplasteragetestapp.access

import uk.co.alt236.codeplasteragetestapp.TestInputLogger

class KotlinWithPrivateCompanionObject {

    fun test(arg: Int) {
        // This should be called
        TestInputLogger.log(arg)
    }

    private companion object {
        const val SOMETHING = 0.003

        fun thisShouldNotBeCalled(arg: Boolean) {
            TestInputLogger.log(arg)
        }
    }
}