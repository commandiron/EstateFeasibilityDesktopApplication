package com.myapp.data.usecase

import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class SaveCalculation @Inject constructor(
    private val myRepo: MyRepo
) {
    suspend operator fun invoke(fizibiliteModel: FizibiliteModel, calculationResult: CalculationResult)

    = myRepo.saveCalculation(fizibiliteModel,calculationResult)
}