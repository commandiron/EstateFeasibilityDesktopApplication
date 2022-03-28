package com.myapp.data.model

@kotlinx.serialization.Serializable
data class CalculationResult(
    //Result
    var toplamBrutAlan: Double = 0.0, //2.07 x 1.35
    var toplamMaliyet: Double = 0.0,
    var hedefGelir: Double = 0.0,
    var muteahhitBrutAlan: Double = 0.0,
    var mutaahhitYuzde : Double = 0.0,
    var katMalikleriBrutAlan: Double = 0.0,
    var katMalikleriYuzde: Double = 0.0
)