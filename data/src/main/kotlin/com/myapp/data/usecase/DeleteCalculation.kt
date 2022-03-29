package com.myapp.data.usecase

import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class DeleteCalculation @Inject constructor(
    private val myRepo: MyRepo
) {
    suspend operator fun invoke(id:String) = myRepo.deleteCalculation(id)
}