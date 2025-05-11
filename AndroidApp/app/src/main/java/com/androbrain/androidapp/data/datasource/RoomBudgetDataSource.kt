package com.androbrain.androidapp.data.datasource

import com.androbrain.androidapp.data.database.BudgetDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoomBudgetDataSource(
    private val budgetDao: BudgetDao,
) : BudgetDataSource {
    override suspend fun insert(budget: BudgetEntity) =
        withContext(Dispatchers.IO) { budgetDao.insert(budget) }

    override suspend fun update(budget: BudgetEntity) =
        withContext(Dispatchers.IO) { budgetDao.update(budget) }

    override suspend fun delete(budget: BudgetEntity) =
        withContext(Dispatchers.IO) { budgetDao.delete(budget) }

    override fun getAll(): Flow<List<BudgetEntity>> = budgetDao.getAll()
}
