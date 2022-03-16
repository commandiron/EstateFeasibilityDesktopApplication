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
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreen
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import com.myapp.ui.feature.first.FirstScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {

    /**
     * Available screensSelectApp
     */
    private sealed class Config : Parcelable {
        object Splash : Config()
        object First : Config()
        object Main : Config()
    }

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
                componentContext = componentContext,
                onSplashFinished = ::onSplashFinished,
            )
            Config.First -> FirstScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                onButtonClicked = ::toSecondScreen
            )
            Config.Main -> SecondScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
        }
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

    /**
     * Invoked when splash finish data sync
     */

    private fun onSplashFinished() {
        router.push(Config.Main)
    }

    private fun toFirstScreen() {
        router.push(Config.First)
    }

    private fun toSecondScreen() {
        router.push(Config.Main)
    }
}