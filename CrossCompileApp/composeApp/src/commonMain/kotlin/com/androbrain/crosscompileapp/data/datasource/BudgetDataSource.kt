package com.androbrain.crosscompileapp.data.datasource

import kotlinx.coroutines.flow.Flow

interface BudgetDataSource {
    suspend fun insert(budget: BudgetEntity)
    suspend fun delete(budget: BudgetEntity)
    fun getAll(): Flow<List<BudgetEntity>>
}
