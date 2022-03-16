package com.myapp.ui.feature.first

import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.repo.MyRepo
import com.myapp.data.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class FirstViewModel @Inject constructor(
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {

    fun getArsaVerileriWithSelenium(fizibiliteModel: FizibiliteModel) {
        viewModelScope.launch {

            myRepo.getArsaVerileriWithSelenium(fizibiliteModel).collect{ response ->
                when(response){
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        println(response.data)
                    }
                    is Response.Error -> {}
                }
            }
        }
    }
}