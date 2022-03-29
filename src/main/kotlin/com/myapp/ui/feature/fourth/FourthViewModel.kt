package com.myapp.ui.feature.fourth

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.collect
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.core.Response
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.model.SavedCalculationDto
import com.myapp.data.usecase.UseCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class FourthViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var savedCalculationFromSavedScreen = mutableStateOf<SavedCalculationDto?>(null)
        private set


    fun saveCalculation(fizibiliteModel: FizibiliteModel, calculationResult: CalculationResult){
        viewModelScope.launch {
            useCases.saveCalculation.invoke(fizibiliteModel, calculationResult).collect { response ->
                when(response) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        println(response.data)
                    }
                    is Response.Error -> {
                    }
                }
            }
        }
    }

    fun loadSingleCalculation(id: String){
        viewModelScope.launch {
            useCases.loadSingleCalculation.invoke(id).collect { response ->
                when(response) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        savedCalculationFromSavedScreen.value = response.data.copy()
                    }
                    is Response.Error -> {
                        println(response.message)
                    }
                }
            }
        }
    }

}