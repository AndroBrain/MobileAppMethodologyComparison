package com.androbrain.androidapp.data.repository

import com.androbrain.androidapp.data.datasource.BudgetEntity
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    suspend fun insert(budget: BudgetModel)
    suspend fun update(budget: BudgetModel)
    suspend fun delete(budget: BudgetModel)
    fun getAll(): Flow<List<BudgetModel>>
}
