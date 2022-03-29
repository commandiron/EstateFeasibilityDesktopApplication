package com.myapp.data.model

import org.kodein.db.model.orm.Metadata

@kotlinx.serialization.Serializable
data class SavedCalculationDto(
    override val id: String,
    val fizibiliteModel: FizibiliteModel,
    val calculationResult: CalculationResult
):Metadata {

}