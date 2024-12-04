package dev.alt236.codeplasteragetestapp.libcomponents

import dev.alt236.codeplasterage.reflection.classfinder.ClassFilterResult
import dev.alt236.codeplasterage.reflection.classfinder.filters.GlobalClassFilter
import dev.alt236.codeplasteragetestapp.dagger.TestDaggerInjectable_Factory
import dev.alt236.codeplasteragetestapp.dagger.TestDaggerInjectionConsumer_MembersInjector
import dev.alt236.codeplasteragetestapp.dagger.di.DaggerTestDaggerComponent
import dev.alt236.codeplasteragetestapp.dagger.di.TestDaggerModule_ProvideInjectableFactory
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertInstanceOf

class GlobalClassFilterTest {
    private lateinit var sut: GlobalClassFilter

    @Before
    fun setUp() {
        sut = GlobalClassFilter()
    }

    @Test
    fun `Dagger - Components should be excluded`() {
        val result = sut.isIncluded(DaggerTestDaggerComponent::class.java)
        assertInstanceOf(ClassFilterResult.Exclude::class.java, result)
        assertInstanceOf(ClassFilterResult.Exclude.ForbiddenClassName::class.java, result)
    }

    @Test
    fun `Dagger - InjectableFactories should be excluded`() {
        val result = sut.isIncluded(TestDaggerModule_ProvideInjectableFactory::class.java)
        assertInstanceOf(ClassFilterResult.Exclude::class.java, result)
        assertInstanceOf(ClassFilterResult.Exclude.ForbiddenClassAncestry::class.java, result)
    }

    @Test
    fun `Dagger - Factories should be excluded`() {
        val result = sut.isIncluded(TestDaggerInjectable_Factory::class.java)
        assertInstanceOf(ClassFilterResult.Exclude::class.java, result)
        assertInstanceOf(ClassFilterResult.Exclude.ForbiddenClassAncestry::class.java, result)
    }

    @Test
    fun `Dagger - MembersInjectors should be excluded`() {
        val result = sut.isIncluded(TestDaggerInjectionConsumer_MembersInjector::class.java)
        assertInstanceOf(ClassFilterResult.Exclude::class.java, result)
        assertInstanceOf(ClassFilterResult.Exclude.ForbiddenClassName::class.java, result)
    }
}
