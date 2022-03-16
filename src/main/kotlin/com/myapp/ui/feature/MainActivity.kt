package com.arsa_fizibilite_app_by_command.ui.feature

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arsa_fizibilite_app_by_command.App
import com.arsa_fizibilite_app_by_command.ui.navigation.NavHostComponent
import com.arsa_fizibilite_app_by_command.ui.value.arsa_fizibilite_app_by_commandTheme
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

            val windowState = rememberWindowState(width = 720.dp, height = 720.dp)

            LifecycleController(lifecycle, windowState)

            Window(
                resizable = false,
                onCloseRequest = ::exitApplication,
                title = "${App.appArgs.appName} (${App.appArgs.version})",
                icon = painterResource("drawables/launcher_icons/system.png"),
                state = windowState,
            ) {
                arsa_fizibilite_app_by_commandTheme {

                    NavHostComponent(DefaultComponentContext(lifecycle)).render()
                }
            }

        }

    }
}