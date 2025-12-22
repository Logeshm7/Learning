package com.kmm.android.di

import com.kmm.android.presentation.AddCarViewModel
import com.kmm.android.presentation.CarImageViewModel
import com.kmm.android.presentation.CarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AddCarViewModel(get(), get(), get(), get())
    }
    viewModel {
        CarImageViewModel(get())
    }
    viewModel {
        CarViewModel(get())
    }
}

