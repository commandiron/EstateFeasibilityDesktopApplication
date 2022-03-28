package com.myapp.data.model

@kotlinx.serialization.Serializable
data class SavedCalculationDto(
    override val id: String,
    val fizibiliteModel: FizibiliteModel,
    val calculationResult: CalculationResult
):org.kodein.db.model.orm.Metadata