package com.kmm.android.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kmm.android.data.db.CarLocalDataSource
import db.CarDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun createDatabase(context: Context): CarDatabase {
    return CarDatabase(AndroidSqliteDriver(CarDatabase.Schema, context, "car_db"))
}

val databasedModule = module {
    single { CarLocalDataSource(createDatabase(androidContext())) }
}