package com.kmm.android.domain

import com.kmm.android.data.Car
import com.kmm.android.data.CarRepository
import com.kmm.android.data.network.ApiResponse

class GetCarsUseCase(private val repo: CarRepository) {
    suspend operator fun invoke(): ApiResponse<List<Car>> {
        try {
            return repo.getCars()
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }
}

class GetCarByIdUseCase(private val repo: CarRepository) {
    suspend operator fun invoke(id: Long): ApiResponse<Car> {
        try {
            return repo.getCar(id)
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }
}

class AddCarUseCase(private val repo: CarRepository) {
    suspend operator fun invoke(car: Car): ApiResponse<Car> {
        try {
            return repo.addCar(car)
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }
}

class UpdateCarUseCase(private val repo: CarRepository) {
    suspend operator fun invoke(car: Car): ApiResponse<Car> {
        try {
            return repo.updateCar(car.id!!, car)
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }
}

class DeleteCarUseCase(private val repo: CarRepository) {
    suspend operator fun invoke(id: Long): ApiResponse<String> {
        try {
            return repo.deleteCar(id)
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }

}

class GetAllCarImageUseCase(private val repo: CarRepository) {
    suspend operator fun invoke(): ApiResponse<List<String>> {
        try {
            return repo.getAllCarImage()
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }
}
