package com.androbrain.androidapp.di

import android.content.Context
import androidx.room.Room
import com.androbrain.androidapp.data.database.AppDatabase
import com.androbrain.androidapp.data.database.BudgetDao
import com.androbrain.androidapp.data.datasource.BudgetDataSource
import com.androbrain.androidapp.data.datasource.RoomBudgetDataSource
import com.androbrain.androidapp.data.repository.BudgetRepository
import com.androbrain.androidapp.data.repository.BudgetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "main_database").build()

    @Provides
    fun provideBudgetDao(database: AppDatabase) = database.budgetDao()

    @Provides
    fun provideBudgetDataSource(
        dao: BudgetDao,
    ): BudgetDataSource = RoomBudgetDataSource(dao)

    @Provides
    fun provideBudgetRepository(
        dataSource: BudgetDataSource,
    ): BudgetRepository = BudgetRepositoryImpl(dataSource)
}
