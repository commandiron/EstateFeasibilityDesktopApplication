package com.myapp.ui.feature.fourth

import kotlinx.coroutines.flow.collect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.WindowState
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.core.Response
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.model.SavedCalculationDto
import com.myapp.data.usecase.UseCases
import kotlinx.coroutines.launch
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.io.File
import javax.imageio.ImageIO
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

    fun getScreenImage(projectName:String, windowState: WindowState){

        val windowPositionX = windowState.position.x.value.toInt() + 10
        val windowPositionY = windowState.position.y.value.toInt() + 36
        val windowSizeX = windowState.size.width.value.toInt() - 20
        val windowsSizeY = windowState.size.height.value.toInt() - 90

        useCases.getScreenImage.invoke(projectName, windowPositionX,windowPositionY,windowSizeX,windowsSizeY)
    }
}