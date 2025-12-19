package com.kmm.android.data

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val id: Long? = null,
    val brand: String,
    val model: String,
    val image: String? = null,
    val price: Double
)