package com.arsa_fizibilite_app_by_command.test

import com.arsa_fizibilite_app_by_command.data.di.module.MyModule
import com.arsa_fizibilite_app_by_command.data.repo.MyRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MyModule::class
        // Add your modules here
    ]
)
interface TestComponent {
    fun myRepo(): MyRepo
}