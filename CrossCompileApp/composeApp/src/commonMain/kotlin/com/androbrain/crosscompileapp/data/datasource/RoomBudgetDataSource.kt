package com.androbrain.crosscompileapp.data.datasource

import com.androbrain.crosscompileapp.data.database.BudgetDao
import com.androbrain.crosscompileapp.data.datasource.BudgetDataSource
import com.androbrain.crosscompileapp.data.datasource.BudgetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoomBudgetDataSource(
    private val budgetDao: BudgetDao,
) : BudgetDataSource {
    override suspend fun insert(budget: BudgetEntity) =
        withContext(Dispatchers.IO) { budgetDao.insert(budget) }

    override suspend fun delete(budget: BudgetEntity) =
        withContext(Dispatchers.IO) { budgetDao.delete(budget) }

    override fun getAll(): Flow<List<BudgetEntity>> = budgetDao.getAll()
}
