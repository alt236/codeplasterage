package dev.alt236.codeplasteragetestapp.dagger.di

import dagger.Module
import dagger.Provides
import dev.alt236.codeplasteragetestapp.dagger.TestDaggerInjectable

@Module
class TestDaggerModule {
    @Provides
    fun provideInjectable(): TestDaggerInjectable = TestDaggerInjectable()
}
