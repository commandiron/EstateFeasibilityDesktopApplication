package com.arsa_fizibilite_app_by_command.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.WindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.essenty.parcelable.Parcelable
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.di.DaggerAppComponent
import com.arsa_fizibilite_app_by_command.ui.feature.main.SecondScreenComponent
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.first.FirstScreenComponent
import com.myapp.ui.feature.third.ThirdScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext,
    private val windowState: WindowState
) : Component, ComponentContext by componentContext {

    private val appComponent: AppComponent = DaggerAppComponent
        .create()

    /**
     * Router configuration
     */
    private val router = router<Config, Component>(
        initialConfiguration = Config.First,
//            FizibiliteModel(
//                projeAdi = "Test1",
//                projeSehir = "İstanbul",
//                projeIlce = "Kadıköy",
//                projeMahalle = "Sahrayıcedit",
//                ada = "371",
//                parsel = "12",
//                arsaAlani = 1200.0,
//                brutAlanBirimSatisFiyati = 30000.0
//                )), //FOR TEST
        handleBackButton = true,
        childFactory = ::createScreenComponent
    )

    /**
     * When a new navigation request made, the screen will be created by this method.
     */
    private fun createScreenComponent(config: Config, componentContext: ComponentContext): Component {

        return when (config) {
            is Config.Splash -> SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
            is Config.First -> FirstScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                firstToSecondScreen = ::firstToSecondScreen
            )
            is Config.Second -> SecondScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                fizibiliteModel = config.fizibiliteModel,
                secondToThirdScreen = ::secondToThirdScreen,
                windowState = windowState
            )
            is Config.Third -> ThirdScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                fizibiliteModel = config.fizibiliteModel
            )
            null -> SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
        }
    }

    private fun firstToSecondScreen(fizibiliteModel: FizibiliteModel) {
        router.replaceCurrent(Config.Second(fizibiliteModel))
    }

    private fun secondToThirdScreen(fizibiliteModel: FizibiliteModel) {
        router.replaceCurrent(Config.Third(fizibiliteModel))
    }


    /**
     * Available screensSelectApp
     */
    private sealed class Config : Parcelable {
        object Splash : Config()
        object First : Config()
        data class Second(val fizibiliteModel: FizibiliteModel) : Config()
        data class Third(val fizibiliteModel: FizibiliteModel) : Config()
    }


    @Composable
    override fun render() {
        Children(
            routerState = router.state,
            animation = crossfadeScale()
        ) { child ->
            child.instance.render()
        }
    }
}