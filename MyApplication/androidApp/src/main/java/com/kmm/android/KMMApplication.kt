package com.kmm.android

import android.app.Application
import com.kmm.android.di.carUseCaseModule
import com.kmm.android.di.databasedModule
import com.kmm.android.di.networkModule
import com.kmm.android.di.networkRepositoryModule
import com.kmm.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KMMApplication : Application() {
    private val module =
        listOf(networkModule, networkRepositoryModule, carUseCaseModule, viewModelModule, databasedModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KMMApplication)
            modules(module)
        }
    }
}