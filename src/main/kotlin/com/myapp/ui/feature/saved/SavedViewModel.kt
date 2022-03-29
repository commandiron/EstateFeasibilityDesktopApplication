package com.myapp.ui.feature.saved

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.collect
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.core.Response
import com.myapp.data.model.SavedCalculationDto
import com.myapp.data.usecase.UseCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var savedCalculations = mutableStateOf<List<SavedCalculationDto>>(listOf())
        private set

    fun loadCalculations(){
        viewModelScope.launch {
            useCases.loadCalculations.invoke().collect { response ->
                when(response){
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        savedCalculations.value = response.data
                    }
                    is Response.Error -> {}
                }
            }
        }
    }

    fun deleteCalculation(id: String){
        viewModelScope.launch {
            useCases.deleteCalculation.invoke(id).collect { response ->
                when(response){
                    is Response.Loading -> {}
                    is Response.Success -> {
                        loadCalculations()
                    }
                    is Response.Error -> {
                        println(response.message)
                    }
                }
            }
        }
    }
}