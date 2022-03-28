package com.myapp.data.usecase

import com.myapp.data.model.FizibiliteModel
import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class GetInAppCalculationForFeasibility @Inject constructor(
    private val myRepo: MyRepo
) {
    suspend operator fun invoke(fizibiliteModel: FizibiliteModel)
            = myRepo.getInAppCalculationForFeasibility(fizibiliteModel)
}