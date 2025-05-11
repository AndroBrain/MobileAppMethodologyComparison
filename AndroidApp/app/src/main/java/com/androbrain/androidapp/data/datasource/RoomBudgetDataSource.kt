package com.androbrain.androidapp.data.datasource

import com.androbrain.androidapp.data.database.BudgetDao
import kotlinx.coroutines.flow.Flow

class RoomBudgetDataSource(
    private val budgetDao: BudgetDao,
) : BudgetDataSource{
    override suspend fun insert(budget: BudgetEntity) = budgetDao.insert(budget)
    override suspend fun update(budget: BudgetEntity) = budgetDao.update(budget)
    override suspend fun delete(budget: BudgetEntity) = budgetDao.delete(budget)
    override fun getAll(): Flow<List<BudgetEntity>> = budgetDao.getAll()
}
