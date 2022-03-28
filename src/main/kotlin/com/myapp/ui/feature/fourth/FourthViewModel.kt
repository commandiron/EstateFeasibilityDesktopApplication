package com.myapp.ui.feature.fourth

import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.usecase.UseCases
import javax.inject.Inject

class FourthViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    init {
        saveCalculationResult()
    }

    fun saveCalculationResult(){
        useCases.saveCalculationResult.invoke()
    }

}