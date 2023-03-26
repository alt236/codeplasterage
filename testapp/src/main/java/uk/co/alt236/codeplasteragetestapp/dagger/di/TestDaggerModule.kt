package uk.co.alt236.codeplasteragetestapp.dagger.di

import dagger.Module
import dagger.Provides
import uk.co.alt236.codeplasteragetestapp.dagger.TestDaggerInjectable

@Module
class TestDaggerModule {

    @Provides
    fun provideInjectable(): TestDaggerInjectable {
        return TestDaggerInjectable()
    }
}
