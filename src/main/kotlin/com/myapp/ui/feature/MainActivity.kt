package com.arsa_fizibilite_app_by_command.ui.feature


import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arsa_fizibilite_app_by_command.App
import com.arsa_fizibilite_app_by_command.ui.navigation.NavHostComponent
import com.arsa_fizibilite_app_by_command.ui.value.arsa_fizibilite_app_by_commandTheme
import com.myapp.data.core.ChromeDriverSeleniumHandle
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import androidx.compose.ui.window.application as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {

    companion object {
        fun getStartIntent(): Intent {
            return Intent(MainActivity::class).apply {
                // data goes here
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        val lifecycle = LifecycleRegistry()

        setContent {

            val windowState = rememberWindowState(position = WindowPosition(Dp(560f),Dp(0f)), width = 800.dp, height = 800.dp)

            LifecycleController(lifecycle, windowState)

            Window(
                resizable = false,
                onCloseRequest = {
                    ChromeDriverSeleniumHandle.quitDriver()
                    exitApplication() },
                title = "${App.appArgs.appName} (${App.appArgs.version})",
                icon = painterResource("drawables/launcher_icons/system.png"),
                state = windowState,
            ) {
                arsa_fizibilite_app_by_commandTheme {
                    NavHostComponent(DefaultComponentContext(lifecycle),windowState).render()
                }
            }
        }
    }
}