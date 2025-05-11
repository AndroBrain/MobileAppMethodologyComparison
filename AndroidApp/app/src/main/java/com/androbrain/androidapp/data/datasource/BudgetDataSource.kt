package com.androbrain.androidapp.data.datasource

import kotlinx.coroutines.flow.Flow

interface BudgetDataSource {
    suspend fun insert(budget: BudgetEntity)
    suspend fun update(budget: BudgetEntity)
    suspend fun delete(budget: BudgetEntity)
    fun getAll(): Flow<List<BudgetEntity>>
    fun getActualBudget(): Flow<Int>
}
