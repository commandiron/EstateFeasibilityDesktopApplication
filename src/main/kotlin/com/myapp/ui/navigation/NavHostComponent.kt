package com.arsa_fizibilite_app_by_command.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.essenty.parcelable.Parcelable
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.di.DaggerAppComponent
import com.arsa_fizibilite_app_by_command.ui.feature.main.SecondScreenComponent
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.first.FirstScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    private val appComponent: AppComponent = DaggerAppComponent
        .create()

    /**
     * Router configuration
     */
    private val router = router<Config, Component>(
        initialConfiguration = Config.First,
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


    /**
     * Available screensSelectApp
     */
    private sealed class Config : Parcelable {
        object Splash : Config()
        object First : Config()
        data class Second(val fizibiliteModel: FizibiliteModel) : Config()
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