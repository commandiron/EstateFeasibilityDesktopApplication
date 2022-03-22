package com.arsa_fizibilite_app_by_command.di

import com.arsa_fizibilite_app_by_command.ui.feature.main.SecondScreenComponent
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import com.myapp.ui.feature.first.FirstScreenComponent
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
    fun inject(firstScreenComponent: FirstScreenComponent)
    fun inject(secondScreenComponent: SecondScreenComponent)
}

//@dagger.Module
//class DatabaseModule{
//    @Provides
//    fun provideDatabase(): Database{
//
//    }
//}