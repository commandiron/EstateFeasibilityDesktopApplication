package com.myapp.ui.feature.third

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.core.Response
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.usecase.UseCases
import com.myapp.data.model.CalculationResult
import com.myapp.data.core.inAppCalculationForFeasibility
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThirdViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var fizibiliteModelFromCalculation: MutableState<CalculationResult?> = mutableStateOf(null)
        private set

    var progressIndicatorDuration = mutableStateOf(0f)
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun calculation(fizibiliteModel: FizibiliteModel){
        viewModelScope.launch {
            useCases.getInAppCalculationForFeasibility.invoke(fizibiliteModel).collect{ response ->
                when(response) {
                    is Response.Loading -> {
                        isLoading.value = true
                        launch {
                            progressIndicatorDuration.value = 0f
                            while(progressIndicatorDuration.value < 1.0f){
                                delay(20)
                                progressIndicatorDuration.value += 0.01f
                            }
                        }
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        fizibiliteModelFromCalculation.value = response.data
                    }
                    is Response.Error -> {
                        println(response.message)
                        isLoading.value = false
                    }
                }
            }
        }
    }
}