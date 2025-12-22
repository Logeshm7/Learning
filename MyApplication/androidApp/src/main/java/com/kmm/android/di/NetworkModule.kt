package com.kmm.android.di

import com.kmm.android.data.network.KtorApiClient
import org.koin.dsl.module

val networkModule = module {
    single {
        KtorApiClient
    }
}