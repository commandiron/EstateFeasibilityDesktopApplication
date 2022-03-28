package com.arsa_fizibilite_app_by_command.ui.feature.main

import androidx.compose.runtime.*
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.router
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.first.FirstViewModel
import com.myapp.ui.feature.second.SecondViewModel
import javax.inject.Inject

class SecondScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val fizibiliteModel: FizibiliteModel,
    private val secondToThirdScreen: (FizibiliteModel) -> Unit,
    private val windowState: WindowState
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: SecondViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        SecondScreen(viewModel, fizibiliteModel, {secondToThirdScreen(it)}, windowState)
    }
}