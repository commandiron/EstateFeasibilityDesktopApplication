package com.myapp.data.usecase

import javax.inject.Inject

data class UseCases @Inject constructor(
    val getArsaAlaniVeMahalleAdiWithSelenium: GetArsaAlaniVeMahalleAdiWithSelenium,
    val loginWebScrapingSite: LoginWebScrapingSite,
    val getBirimSatisFiyatiWithSelenium: GetBirimSatisFiyatiWithSelenium,
    val getInAppCalculationForFeasibility: GetInAppCalculationForFeasibility,
    val saveCalculation: SaveCalculation,
    val loadCalculations: LoadCalculations,
    val deleteCalculation: DeleteCalculation,
    val loadSingleCalculation: LoadSingleCalculation,
    val getScreenImage: GetScreenImage
) {
}