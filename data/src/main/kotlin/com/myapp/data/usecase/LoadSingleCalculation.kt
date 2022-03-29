package com.myapp.data.usecase

import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class LoadSingleCalculation  @Inject constructor(
    private val myRepo: MyRepo
) {
    suspend operator fun invoke(id:String) = myRepo.loadSingleCalculation(id)
}