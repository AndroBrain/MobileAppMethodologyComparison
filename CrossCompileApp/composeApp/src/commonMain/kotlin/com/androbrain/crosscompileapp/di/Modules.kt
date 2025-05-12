package com.androbrain.crosscompileapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.androbrain.crosscompileapp.data.database.AppDatabase
import com.androbrain.crosscompileapp.data.database.DatabaseFactory
import com.androbrain.crosscompileapp.data.datasource.BudgetDataSource
import com.androbrain.crosscompileapp.data.datasource.RoomBudgetDataSource
import com.androbrain.crosscompileapp.data.repository.BudgetRepository
import com.androbrain.crosscompileapp.data.repository.BudgetRepositoryImpl
import com.androbrain.crosscompileapp.ui.dashboard.DashboardViewModel
import com.androbrain.crosscompileapp.ui.dashboard.edit.EditBudgetViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<AppDatabase>().budgetDao() }

    singleOf(::RoomBudgetDataSource).bind<BudgetDataSource>()
    singleOf(::BudgetRepositoryImpl).bind<BudgetRepository>()

    viewModelOf(::DashboardViewModel)
    viewModelOf(::EditBudgetViewModel)
}
