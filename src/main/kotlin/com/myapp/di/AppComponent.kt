package com.arsa_fizibilite_app_by_command.di

import com.arsa_fizibilite_app_by_command.ui.feature.main.SecondScreenComponent
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import com.myapp.ui.feature.first.FirstScreenComponent
import com.myapp.ui.feature.fourth.FourthScreenComponent
import com.myapp.ui.feature.saved.SavedScreenComponent
import com.myapp.ui.feature.settings.SettingsScreenComponent
import com.myapp.ui.feature.third.ThirdScreenComponent
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
    fun inject(thirdScreenComponent: ThirdScreenComponent)
    fun inject(fourthScreenComponent: FourthScreenComponent)
    fun inject(settingsScreenComponent: SettingsScreenComponent)
    fun inject(savedScreenComponent: SavedScreenComponent)
}

//@dagger.Module
//class DatabaseModule{
//    @Provides
//    fun provideDatabase(): Database{
//
//    }
//}