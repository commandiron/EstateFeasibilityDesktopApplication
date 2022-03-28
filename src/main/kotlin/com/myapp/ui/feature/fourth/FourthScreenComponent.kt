package com.myapp.ui.feature.fourth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.ui.navigation.Component
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import javax.inject.Inject

class FourthScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val calculationResult: CalculationResult,
    private val fizibiliteModel: FizibiliteModel
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var viewModel: FourthViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        FourthScreen(viewModel, calculationResult, fizibiliteModel)
    }
}