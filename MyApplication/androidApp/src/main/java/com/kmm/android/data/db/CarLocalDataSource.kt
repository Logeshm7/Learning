package com.kmm.android.data.db

import com.kmm.android.data.ApiResponse
import com.kmm.android.data.Car
import com.kmm.android.domain.toCar
import db.CarDatabase


class CarLocalDataSource(carDb: CarDatabase) {
    private val carDbQueries = carDb.carEntityQueries

    suspend fun getCars(): ApiResponse<List<Car>> {
        return ApiResponse(
            success = true,
            message = "Cars fetched successfully",
            data = carDbQueries.getAllCar().executeAsList().map { it.toCar() })
    }

    suspend fun getCar(id: Long): ApiResponse<Car> {
        return ApiResponse(
            success = true,
            message = "Car found",
            data = carDbQueries.getCarById(id).executeAsOne().toCar()
        )
    }

    suspend fun addCar(car: Car): ApiResponse<Car> {
        carDbQueries.insertCar(
            brand = car.brand,
            price = car.price,
            image = car.image,
            model = car.model,
        )
        return ApiResponse(success = true, message = "Car added successfully", data = car)
    }

    suspend fun updateCar(car: Car): ApiResponse<Car> {
        carDbQueries.updateCar(
            id = car.id!!,
            brand = car.brand,
            price = car.price,
            image = car.image,
            model = car.model,
        )
        return ApiResponse(success = true, message = "Car updated successfully", data = car)
    }

    suspend fun deleteCar(id: Long): ApiResponse<String> {
        carDbQueries.deleteCar(id)
        return ApiResponse(success = true, message = "Car deleted successfully", data = null)
    }

    suspend fun getAllCarImage(): ApiResponse<List<String>> {
        return ApiResponse(
            success = true,
            message = "Cars Image fetched successfully",
            data = carDbQueries.getAllCarImage().executeAsList().mapNotNull { it.image })
    }
}