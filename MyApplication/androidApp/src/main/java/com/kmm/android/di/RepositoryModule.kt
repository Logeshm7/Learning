package com.kmm.android.di

import com.kmm.android.data.CarRepository
import com.kmm.android.data.CarRepositoryImpl
import org.koin.dsl.module

val networkRepositoryModule = module{
    single<CarRepository> {
        CarRepositoryImpl(get(),get())
    }
}