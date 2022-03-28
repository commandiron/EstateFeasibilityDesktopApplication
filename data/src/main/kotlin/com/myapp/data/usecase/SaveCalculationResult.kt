package com.myapp.data.usecase

import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class SaveCalculationResult @Inject constructor(
    private val myRepo: MyRepo
) {
    operator fun invoke() = myRepo.saveCalculationResult()
}