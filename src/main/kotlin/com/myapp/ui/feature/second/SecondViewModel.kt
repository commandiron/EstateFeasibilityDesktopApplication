package com.myapp.ui.feature.second

import kotlinx.coroutines.flow.collect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.WindowPosition
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.usecase.UseCases
import com.myapp.data.core.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var fizibiliteModelFromInternet = mutableStateOf(FizibiliteModel())
        private set

    var progressIndicatorDuration = mutableStateOf(0f)
        private set

    var isLoading = mutableStateOf(false)
        private set

    var dataIsLoaded = mutableStateOf(false)
        private set

    var isErrorHappen = mutableStateOf(false)

    fun loginWebScrapingSite(windowPosition: WindowPosition, windowSize: DpSize){
        viewModelScope.launch {
            useCases.loginWebScrapingSite.invoke(windowPosition.x.value, windowPosition.y.value + windowSize.height.value/2).collect { response ->
                when(response) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                    }
                    is Response.Error -> {
                    }
                }
            }
        }
    }

    fun getBirimSatisFiyatiWithSelenium(fizibiliteModel: FizibiliteModel){
        viewModelScope.launch {
            useCases.getBirimSatisFiyatiWithSelenium.invoke(fizibiliteModel).collect { response ->
                when(response) {
                    is Response.Loading -> {
                        isLoading.value = true
                        isErrorHappen.value = false

                        while(progressIndicatorDuration.value < 1.0f){
                            delay(20)
                            progressIndicatorDuration.value += 0.0019f
                        }
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        fizibiliteModelFromInternet.value = response.data
                        dataIsLoaded.value = true
                    }
                    is Response.Error -> {
                        println(response.message)
                        isLoading.value = false
                        isErrorHappen.value = true
                    }
                }
            }
        }
    }
}