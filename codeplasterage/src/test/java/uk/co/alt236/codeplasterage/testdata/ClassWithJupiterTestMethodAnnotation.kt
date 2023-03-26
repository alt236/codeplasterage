package uk.co.alt236.codeplasterage.testdata

import org.junit.jupiter.api.Test

class ClassWithJupiterTestMethodAnnotation {

    @Test
    fun methodWithTestAnnotation() {
        // Even though this test will be executed, we only need this class to check reflection
        return
    }
}
