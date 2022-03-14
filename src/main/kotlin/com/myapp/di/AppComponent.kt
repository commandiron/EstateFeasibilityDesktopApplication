package com.arsa_fizibilite_app_by_command.di

import com.arsa_fizibilite_app_by_command.ui.feature.main.MainScreenComponent
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Add your modules here
    ]
)
interface AppComponent {
    fun inject(splashScreenComponent: SplashScreenComponent)
    fun inject(mainScreenComponent: MainScreenComponent)
}