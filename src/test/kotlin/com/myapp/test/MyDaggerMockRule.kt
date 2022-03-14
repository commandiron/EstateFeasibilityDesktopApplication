package com.arsa_fizibilite_app_by_command_.test

import com.arsa_fizibilite_app_by_command_.data.di.module.MyModule
import it.cosenonjaviste.daggermock.DaggerMockRule

class MyDaggerMockRule : DaggerMockRule<TestComponent>(
    TestComponent::class.java,
    MyModule()
    // TODO : Add your modules here
) {
    init {
        customizeBuilder<DaggerTestComponent.Builder> {
            it
        }
    }
}