package com.arsa_fizibilite_app_by_command_.test

import com.arsa_fizibilite_app_by_command_.data.di.module.MyModule
import com.arsa_fizibilite_app_by_command_.data.repo.MyRepo
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