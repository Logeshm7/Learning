package com.kmm.android.di

import com.kmm.android.domain.AddCarUseCase
import com.kmm.android.domain.DeleteCarUseCase
import com.kmm.android.domain.GetAllCarImageUseCase
import com.kmm.android.domain.GetCarByIdUseCase
import com.kmm.android.domain.GetCarsUseCase
import com.kmm.android.domain.UpdateCarUseCase
import org.koin.dsl.module

val carUseCaseModule = module {
    factory {
        GetCarsUseCase(get())
    }
    factory {
        GetCarByIdUseCase(get())
    }
    factory {
        AddCarUseCase(get())
    }
    factory {
        UpdateCarUseCase(get())
    }
    factory {
        DeleteCarUseCase(get())
    }
    factory {
        GetAllCarImageUseCase(get())
    }
}