package com.androbrain.androidapp.data.datasource

import kotlinx.coroutines.flow.Flow

class RoomBudgetDataSource(
    private val budgetDao: BudgetDao,
) : BudgetDataSource{
    override suspend fun insert(budget: BudgetEntity) = budgetDao.insert(budget)
    override suspend fun update(budget: BudgetEntity) = budgetDao.update(budget)
    override suspend fun delete(budget: BudgetEntity) = budgetDao.delete(budget)
    override fun getAll(): Flow<List<BudgetEntity>> = budgetDao.getAll()
    override fun getActualBudget(): Flow<Int> {
        // Figure out if it's needed here and not in repo or viewmodel instead
        TODO("Not yet implemented")
    }
}
