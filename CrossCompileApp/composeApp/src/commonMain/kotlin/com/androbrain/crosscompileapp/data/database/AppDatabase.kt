package com.androbrain.crosscompileapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.androbrain.crosscompileapp.data.datasource.BudgetEntity

@Database(entities = [BudgetEntity::class], version = 2)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao

    companion object {
        const val NAME = "app.db"
    }
}
