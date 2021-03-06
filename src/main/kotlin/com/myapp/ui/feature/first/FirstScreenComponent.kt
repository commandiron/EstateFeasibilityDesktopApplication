package com.myapp.ui.feature.first

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import com.myapp.data.model.FizibiliteModel
import javax.inject.Inject

class FirstScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val firstToSecondScreen: (FizibiliteModel) -> Unit,
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: FirstViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        FirstScreen(viewModel, {firstToSecondScreen(it)})
    }
}