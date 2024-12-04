package dev.alt236.codeplasteragetestapp.dagger

import dev.alt236.codeplasteragetestapp.dagger.di.DaggerTestDaggerComponent
import javax.inject.Inject

class TestDaggerInjectionConsumer {
    @Inject
    lateinit var injectable: TestDaggerInjectable

    init {
        DaggerTestDaggerComponent.create().inject(this)
    }
}
