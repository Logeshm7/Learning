package com.kmm.android.data

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val id: Long? = null,
    val brand: String,
    val model: String,
    var image: String? = null,
    val price: Double
)