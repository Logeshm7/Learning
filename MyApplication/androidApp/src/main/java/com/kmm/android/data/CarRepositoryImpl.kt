package com.kmm.android.data

import com.kmm.android.data.db.CarLocalDataSource
import com.kmm.android.data.network.KtorApiClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CarRepositoryImpl(
    private val ktorClient: KtorApiClient,
    private val carLocalDataSource: CarLocalDataSource,
) : CarRepository {
    private var internetConnected: Boolean = false
    override suspend fun getCars(): ApiResponse<List<Car>> {
        if (internetConnected) {
            return ktorClient.client.get(KtorApiClient.ENDPOINT).body()
        }
        return carLocalDataSource.getCars()
    }

    override suspend fun getCar(id: Long): ApiResponse<Car> {
        if (internetConnected) {
            return ktorClient.client.get(KtorApiClient.ENDPOINT + "/$id").body()
        }
        return carLocalDataSource.getCar(id)
    }

    override suspend fun addCar(car: Car): ApiResponse<Car> {
        if (internetConnected) {
            return ktorClient.client.post(KtorApiClient.ENDPOINT) {
                contentType(ContentType.Application.Json)
                setBody(car)
            }.body()
        }
        return carLocalDataSource.addCar(car)
    }

    override suspend fun updateCar(id: Long, car: Car): ApiResponse<Car> {
        if (internetConnected) {
            return ktorClient.client.put(KtorApiClient.ENDPOINT + "/$id") {
                contentType(ContentType.Application.Json)
                setBody(car)
            }.body()
        }
        return carLocalDataSource.updateCar(car)
    }

    override suspend fun deleteCar(id: Long): ApiResponse<String> {
        if (internetConnected) {
            return ktorClient.client.delete(KtorApiClient.ENDPOINT + "/$id").body()
        }
        return carLocalDataSource.deleteCar(id)
    }

    override suspend fun getAllCarImage(): ApiResponse<List<String>> {
        if (internetConnected) {
            return ktorClient.client.get(KtorApiClient.GETALLCARIMAGEENDPOINT).body()
        }
        return carLocalDataSource.getAllCarImage()
    }
}