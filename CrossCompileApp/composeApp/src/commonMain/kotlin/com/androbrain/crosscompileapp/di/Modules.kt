package com.androbrain.crosscompileapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.androbrain.crosscompileapp.data.database.AppDatabase
import com.androbrain.crosscompileapp.data.database.DatabaseFactory
import com.androbrain.crosscompileapp.data.repository.BudgetRepository
import com.androbrain.crosscompileapp.data.repository.BudgetRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::BudgetRepositoryImpl).bind<BudgetRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<AppDatabase>().budgetDao() }

//    viewModelOf(::BookListViewModel)
//    viewModelOf(::BookDetailViewModel)
//    viewModelOf(::SelectedBookViewModel)
}
