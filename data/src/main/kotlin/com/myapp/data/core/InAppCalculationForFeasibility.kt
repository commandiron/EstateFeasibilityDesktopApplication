package com.myapp.data.core

import com.myapp.data.model.FizibiliteModel
import com.myapp.data.model.CalculationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.RoundingMode

object inAppCalculationForFeasibility {

    fun calculate(inputFizibiliteModel: FizibiliteModel): CalculationResult {

        val arsaAlani = inputFizibiliteModel.arsaAlani
        val insaatBirimMaliyeti = inputFizibiliteModel.insaatBirimMaliyeti
        val hedefKarOrani = inputFizibiliteModel.hedefKarOrani
        val brutAlanBirimSatisFiyati = inputFizibiliteModel.brutAlanBirimSatisFiyati

        val toplamBrutAlan = arsaAlani.times(2.07).times(1.30)
        val toplamMaliyet = toplamBrutAlan.times(insaatBirimMaliyeti)
        val hedefGelir = toplamMaliyet.times(1+hedefKarOrani)
        val muteahhitBrutAlan = hedefGelir / brutAlanBirimSatisFiyati
        val mutaahhitYuzde = muteahhitBrutAlan / toplamBrutAlan
        val katMalikleriBrutAlan = toplamBrutAlan - muteahhitBrutAlan
        val katMalikleriYuzde = katMalikleriBrutAlan / toplamBrutAlan

        val calculationResult =
            CalculationResult(
                toplamBrutAlan = toplamBrutAlan,
                toplamMaliyet = toplamMaliyet,
                hedefGelir = hedefGelir,
                muteahhitBrutAlan = muteahhitBrutAlan,
                mutaahhitYuzde = mutaahhitYuzde,
                katMalikleriBrutAlan = katMalikleriBrutAlan,
                katMalikleriYuzde = katMalikleriYuzde)

        println(calculationResult)

        return calculationResult
    }
}