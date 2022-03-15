package com.myapp.ui.feature.first

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.myapp.ui.feature.AppViewModel
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import javax.inject.Inject

class FirstScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val onButtonClicked: () -> Unit,
) : Component, ComponentContext by componentContext {
    @Inject
    lateinit var viewModel: AppViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        FirstScreen(viewModel, onButtonClicked)
    }
}