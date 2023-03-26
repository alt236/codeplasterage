package uk.co.alt236.codeplasteragetestapp.dagger

import uk.co.alt236.codeplasteragetestapp.dagger.di.DaggerTestDaggerComponent
import javax.inject.Inject

class TestDaggerInjectionConsumer {

    @Inject
    lateinit var injectable: TestDaggerInjectable

    init {
        DaggerTestDaggerComponent.create().inject(this)
    }
}
