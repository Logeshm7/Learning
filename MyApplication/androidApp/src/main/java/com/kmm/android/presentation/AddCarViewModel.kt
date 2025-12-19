package com.kmm.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmm.android.data.Car
import com.kmm.android.domain.AddCarUseCase
import com.kmm.android.domain.DeleteCarUseCase
import com.kmm.android.domain.GetCarByIdUseCase
import com.kmm.android.domain.UpdateCarUseCase
import kotlinx.coroutines.launch

class AddCarViewModel(
    private val addCar: AddCarUseCase,
    private val getCarByIdUseCase: GetCarByIdUseCase,
    private val _updateCar: UpdateCarUseCase,
    private val _deleteCar: DeleteCarUseCase,
) : ViewModel() {

    private val _exit = MutableLiveData<Boolean>()
    val exit: LiveData<Boolean> get() = _exit

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _car = MutableLiveData<Car>()
    val car: LiveData<Car> get() = _car

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun getCar(id: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val apiResponse = getCarByIdUseCase(id)
            _isLoading.value = false
            if (apiResponse.success) {
                _car.postValue(apiResponse.data)
                apiResponse.message.let {
                    _message.postValue(apiResponse.message)
                }
            } else {
                apiResponse.message.let {
                    _error.postValue(apiResponse.message)
                }
            }
        }
    }

    fun createCar(car: Car) {
        viewModelScope.launch {
            if (checkCarData(car)) {
                _isLoading.value = true
                val apiResponse = addCar(car)
                _isLoading.value = false
                if (apiResponse.success) {
                    apiResponse.message.let {
                        _message.postValue(apiResponse.message)
                    }
                } else {
                    apiResponse.message.let {
                        _error.postValue(apiResponse.message)
                    }
                }
            }
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch {
            if (checkCarData(car)) {
                _isLoading.value = true
                val apiResponse = _updateCar(car)
                _isLoading.value = false
                if (apiResponse.success) {
                    apiResponse.message.let {
                        _message.postValue(apiResponse.message)
                    }
                    _exit.value = true
                } else {
                    apiResponse.message.let {
                        _error.postValue(apiResponse.message)
                    }
                }
            }
        }
    }

    fun deleteCar(id: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            var apiResponse = _deleteCar(id)
            _isLoading.value = false
            if (apiResponse.success) {
                apiResponse.message.let {
                    _message.postValue(apiResponse.message)
                }
                _exit.value = true
            } else {
                apiResponse.message.let {
                    _error.postValue(apiResponse.message)
                }
            }
        }
    }

    private fun checkCarData(car: Car): Boolean {
        if (car.brand.isEmpty()) {
            _error.postValue("Brand name cannot be empty")
            return false
        }
        if (car.model.isEmpty()) {
            _error.postValue("Model cannot be empty")
            return false
        }
        if (car.price < 1000) {
            _error.postValue("Price cannot be empty less than 1000")
            return false
        }
        return true
    }
}