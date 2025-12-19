package com.kmm.android.data

import com.kmm.android.data.network.ApiResponse

interface CarRepository {
    suspend fun getCars(): ApiResponse<List<Car>>
    suspend fun getCar(id: Long): ApiResponse<Car>
    suspend fun addCar(car: Car): ApiResponse<Car>
    suspend fun updateCar(id: Long, car: Car): ApiResponse<Car>
    suspend fun deleteCar(id: Long): ApiResponse<String>
    suspend fun getAllCarImage(): ApiResponse<List<String>>
}