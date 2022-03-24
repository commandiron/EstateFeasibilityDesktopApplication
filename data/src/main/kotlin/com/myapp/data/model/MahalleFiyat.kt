package com.myapp.data.model

import org.kodein.db.model.Id
import org.kodein.db.model.orm.Metadata

@kotlinx.serialization.Serializable
data class MahalleFiyat(
    @Id override val id: String,
    val mahalle: String,
    val fiyat: String
): Metadata {
//Serialiazer olmadığından şu an için  yapamıyorum
}



