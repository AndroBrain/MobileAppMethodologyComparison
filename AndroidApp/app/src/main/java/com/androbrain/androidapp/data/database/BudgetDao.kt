package com.androbrain.androidapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.androbrain.androidapp.data.datasource.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(budget: BudgetEntity)

    @Delete
    fun delete(budget: BudgetEntity)

    @Update
    fun update(budget: BudgetEntity)

    @Query("SELECT * FROM BudgetEntity")
    fun getAll(): Flow<List<BudgetEntity>>
}
