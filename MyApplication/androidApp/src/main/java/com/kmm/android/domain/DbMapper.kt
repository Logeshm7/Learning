package com.kmm.android.domain

import com.kmm.android.data.Car
import db.CarEntity

fun CarEntity.toCar() = Car(
    id = id,
    brand = brand,
    model = model,
    image = image,
    price = price
)