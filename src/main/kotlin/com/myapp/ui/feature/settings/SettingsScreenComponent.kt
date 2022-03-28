package com.myapp.ui.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import javax.inject.Inject

class SettingsScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: SettingsViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        SettingsScreen(viewModel)
    }
}