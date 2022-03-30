package com.myapp.data.usecase

import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class LoadCalculations @Inject constructor(
    private val myRepo: MyRepo
) {
    suspend operator fun invoke() = myRepo.loadCalculations()
}