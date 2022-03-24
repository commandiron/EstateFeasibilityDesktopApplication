package com.myapp.ui.feature.third

import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import com.myapp.data.model.FizibiliteModel
import javax.inject.Inject

class ThirdScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val fizibiliteModel: FizibiliteModel
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: ThirdViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        ThirdScreen(viewModel, fizibiliteModel)
    }
}