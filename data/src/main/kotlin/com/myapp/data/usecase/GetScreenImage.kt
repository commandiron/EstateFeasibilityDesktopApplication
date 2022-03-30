package com.myapp.data.usecase

import com.myapp.data.repo.MyRepo
import javax.inject.Inject

class GetScreenImage @Inject constructor(
    private val myRepo: MyRepo
) {
    operator fun invoke(projectName: String, windowPositionX: Int, windowPositionY: Int, windowSizeX:Int, windowsSizeY: Int)
    = myRepo.getScreenImage(projectName,windowPositionX,windowPositionY,windowSizeX,windowsSizeY)
}