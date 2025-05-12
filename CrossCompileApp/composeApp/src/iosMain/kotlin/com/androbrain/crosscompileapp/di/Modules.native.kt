package com.androbrain.crosscompileapp.di

import com.androbrain.crosscompileapp.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory() }
    }