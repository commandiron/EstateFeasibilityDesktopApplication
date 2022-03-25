package com.myapp.ui.feature.first

import kotlinx.coroutines.flow.collect
import androidx.compose.runtime.mutableStateOf
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.usecase.UseCases
import com.myapp.data.core.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val useCases: UseCases,
    // Inject your repos here...
) : ViewModel() {

    var progressIndicatorDuration = mutableStateOf(0f)
        private set

    var fizibiliteModelFromInternet = mutableStateOf(FizibiliteModel())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var dataIsLoaded = mutableStateOf(false)

    var isErrorHappen = mutableStateOf(false)

    fun getArsaAlaniVeMahalleAdiWithSelenium(fizibiliteModel: FizibiliteModel) {
        viewModelScope.launch {
            useCases.getArsaAlaniVeMahalleAdiWithSelenium.invoke(fizibiliteModel).collect{ response ->
                when(response){
                    is Response.Loading -> {
                        isLoading.value = true
                        isErrorHappen.value = false

                        while(progressIndicatorDuration.value < 1.0f){
                            delay(20)
                            progressIndicatorDuration.value += 0.002f
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