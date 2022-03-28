package com.myapp.ui.feature.saved

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import com.myapp.ui.feature.settings.SettingsScreen
import com.myapp.ui.feature.settings.SettingsViewModel
import javax.inject.Inject

class SavedScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: SavedViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        SavedScreen(viewModel)
    }
}