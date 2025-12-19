package com.kmm.android.data.network

import android.util.Log
import com.kmm.android.data.Car
import com.kmm.android.data.CarRepository
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorCarRepository(private val ktorClient:KtorApiClient) : CarRepository {
    override suspend fun getCars(): ApiResponse<List<Car>> {
        return ktorClient.client.get(ktorClient.ENDPOINT).body()
    }

    override suspend fun getCar(id: Long): ApiResponse<Car> {
        return ktorClient.client.get(ktorClient.ENDPOINT+"/$id").body()
    }

    override suspend fun addCar(car: Car): ApiResponse<Car> {
        return ktorClient.client.post(ktorClient.ENDPOINT){
            contentType(ContentType.Application.Json)
            setBody(car)
        }.body()
    }

    override suspend fun updateCar(id : Long, car: Car): ApiResponse<Car> {
        return ktorClient.client.put(ktorClient.ENDPOINT+"/$id"){
            contentType(ContentType.Application.Json)
            setBody(car)
        }.body()
    }

    override suspend fun deleteCar(id: Long): ApiResponse<String> {
        Log.d("TAG", "deleteCar: === ")
        return ktorClient.client.delete(ktorClient.ENDPOINT+"/$id").body()
    }

    override suspend fun getAllCarImage(): ApiResponse<List<String>> {
        return ktorClient.client.get(ktorClient.GETALLCARIMAGEENDPOINT).body()
    }
}