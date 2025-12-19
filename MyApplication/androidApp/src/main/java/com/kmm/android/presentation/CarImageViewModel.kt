package com.kmm.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmm.android.data.Car
import com.kmm.android.domain.GetAllCarImageUseCase
import kotlinx.coroutines.launch

class CarImageViewModel (private val getAllCarImageUseCase: GetAllCarImageUseCase): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _car = MutableLiveData<List<String>>()
    val car = _car

    private val _error = MutableLiveData<String>()
    val error = _error

    private val _message = MutableLiveData<String>()
    val message = _message

    fun getCarImage() {
        _isLoading.value = true
        viewModelScope.launch {
            val apiResponse = getAllCarImageUseCase()
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
}