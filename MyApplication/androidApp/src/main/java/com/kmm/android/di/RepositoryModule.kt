package com.kmm.android.di

import com.kmm.android.data.CarRepository
import com.kmm.android.data.network.KtorCarRepositoryImpl
import org.koin.dsl.module

val networkRepositoryModule = module{
    single<CarRepository> {
        KtorCarRepositoryImpl(get())
    }
}