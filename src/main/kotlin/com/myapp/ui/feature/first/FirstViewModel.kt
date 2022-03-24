package com.myapp.ui.feature.first

import kotlinx.coroutines.flow.collect
import androidx.compose.runtime.mutableStateOf
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.usecase.UseCases
import com.myapp.data.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val useCases: UseCases,
    // Inject your repos here...
) : ViewModel() {

    var fizibiliteModelFromInternet = mutableStateOf(FizibiliteModel())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var dataIsLoaded = mutableStateOf(false)

    fun getArsaAlaniVeMahalleAdiWithSelenium(fizibiliteModel: FizibiliteModel) {
        viewModelScope.launch {
            useCases.getArsaAlaniVeMahalleAdiWithSelenium.invoke(fizibiliteModel).collect{ response ->
                when(response){
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        fizibiliteModelFromInternet.value = response.data
                        dataIsLoaded.value = true
                    }
                    is Response.Error -> {
                    }
                }
            }
        }
    }
}