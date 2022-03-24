package com.myapp.data.usecase

import com.myapp.data.repo.MyRepo
import org.intellij.lang.annotations.Flow
import javax.inject.Inject
import org.openqa.selenium.Point

class LoginWebScrapingSite @Inject constructor(
    private val myRepo: MyRepo
) {
    suspend operator fun invoke(windowPositionX: Float, windowPositionY: Float) = myRepo.loginWebScrapingSite(windowPositionX,windowPositionY)
}