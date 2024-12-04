package dev.alt236.codeplasteragetestapp.libcomponents

import dev.alt236.codeplasterage.reflection.classfinder.filters.checks.CheckForbiddenName
import dev.alt236.codeplasteragetestapp.dagger.di.DaggerTestDaggerComponent
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class CheckForbiddenNameTest(
    private val clazz: Class<*>,
    private val expected: Boolean,
) {
    private lateinit var sut: CheckForbiddenName

    @Before
    fun setUp() {
        sut = CheckForbiddenName()
    }

    @Test
    fun testHasForbiddenName() {
        assertEquals(expected, sut.isForbidden(clazz))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: isValid({0})={1}")
        fun data(): Iterable<Array<Any>> =
            arrayListOf(
                arrayOf(String::class.java, false),
                arrayOf(DaggerTestDaggerComponent::class.java, true),
            )
    }
}
