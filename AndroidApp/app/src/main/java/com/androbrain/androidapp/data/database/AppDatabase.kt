package com.androbrain.androidapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androbrain.androidapp.data.datasource.BudgetEntity

@Database(entities = [BudgetEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao
}
