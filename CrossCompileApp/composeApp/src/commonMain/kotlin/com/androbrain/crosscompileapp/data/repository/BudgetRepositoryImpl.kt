package com.androbrain.crosscompileapp.data.repository

import com.androbrain.crosscompileapp.data.datasource.BudgetDataSource
import com.androbrain.crosscompileapp.data.datasource.BudgetEntity
import com.androbrain.crosscompileapp.data.mapping.toEntity
import com.androbrain.crosscompileapp.data.mapping.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BudgetRepositoryImpl(
    private val budgetDataSource: BudgetDataSource,
) : BudgetRepository {
    override suspend fun insert(budget: BudgetModel) = budgetDataSource.insert(budget.toEntity())

    override suspend fun delete(budget: BudgetModel) = budgetDataSource.delete(budget.toEntity())

    override fun getAll(): Flow<List<BudgetModel>> = budgetDataSource.getAll()
        .map { it.map(BudgetEntity::toModel) }
}
