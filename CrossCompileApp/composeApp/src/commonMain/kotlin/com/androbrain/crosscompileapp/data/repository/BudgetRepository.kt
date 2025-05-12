package com.androbrain.crosscompileapp.data.repository

import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    suspend fun insert(budget: BudgetModel)
    suspend fun delete(budget: BudgetModel)
    fun getAll(): Flow<List<BudgetModel>>
}
