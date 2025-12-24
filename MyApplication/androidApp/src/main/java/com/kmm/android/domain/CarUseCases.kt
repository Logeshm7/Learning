package com.kmm.android.domain

import com.kmm.android.data.ApiResponse
import com.kmm.android.data.Car
import com.kmm.android.data.CarRepository

open class BaseCarUseCases {
    protected val carImageArray = ArrayList(
        listOf(
            "https://images.pexels.com/photos/810357/pexels-photo-810357.jpeg",
            "https://images.pexels.com/photos/244206/pexels-photo-244206.jpeg",
            "https://images.pexels.com/photos/10256429/pexels-photo-10256429.jpeg",
            "https://images.pexels.com/photos/29352558/pexels-photo-29352558.jpeg",
            "https://images.pexels.com/photos/27019870/pexels-photo-27019870.jpeg",
            "https://images.pexels.com/photos/12866903/pexels-photo-12866903.jpeg",
            "https://images.pexels.com/photos/193991/pexels-photo-193991.jpeg",
            "https://images.pexels.com/photos/193995/pexels-photo-193995.jpeg",
            "https://images.pexels.com/photos/4096527/pexels-photo-4096527.jpeg",
            "https://images.pexels.com/photos/9162106/pexels-photo-9162106.jpeg",
            "https://images.pexels.com/photos/10358231/pexels-photo-10358231.jpeg"
        )
    )
}

class GetCarsUseCase(private val repo: CarRepository) {
    //    var rand: Random = Random()
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

class AddCarUseCase(private val repo: CarRepository) : BaseCarUseCases() {
    suspend operator fun invoke(car: Car): ApiResponse<Car> {
        try {
            if (car.image.isNullOrEmpty())
                car.image = carImageArray.random()
            return repo.addCar(car)
        } catch (e: Exception) {
            return ApiResponse(message = e.message ?: ("something went wrong"))
        }
    }
}

class UpdateCarUseCase(private val repo: CarRepository) : BaseCarUseCases() {
    suspend operator fun invoke(car: Car): ApiResponse<Car> {
        try {
            if (car.image.isNullOrEmpty())
                car.image = carImageArray.random()
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
