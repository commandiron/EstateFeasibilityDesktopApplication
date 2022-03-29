package com.myapp.ui.feature.saved

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import com.myapp.data.model.SavedCalculationDto
import com.myapp.ui.feature.settings.SettingsScreen
import com.myapp.ui.feature.settings.SettingsViewModel
import javax.inject.Inject

class SavedScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val savedToFourthScreen:(selectedItemId: String)  -> Unit
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: SavedViewModel

    init {
        appComponent.inject(this)
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        SavedScreen(viewModel,{savedToFourthScreen(it)})
    }
}