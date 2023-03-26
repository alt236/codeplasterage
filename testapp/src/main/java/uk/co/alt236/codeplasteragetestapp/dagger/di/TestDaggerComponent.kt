package uk.co.alt236.codeplasteragetestapp.dagger.di

import dagger.Component
import uk.co.alt236.codeplasteragetestapp.dagger.TestDaggerInjectable
import uk.co.alt236.codeplasteragetestapp.dagger.TestDaggerInjectionConsumer
import javax.inject.Singleton

@Component(modules = [TestDaggerModule::class])
@Singleton
interface TestDaggerComponent {
    fun getInjectable(): TestDaggerInjectable
    fun inject(consumer: TestDaggerInjectionConsumer)
}
