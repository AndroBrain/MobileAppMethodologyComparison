package com.androbrain.androidapp.data.mapping

import com.androbrain.androidapp.data.datasource.BudgetEntity
import com.androbrain.androidapp.data.repository.BudgetModel

fun BudgetEntity.toModel() = BudgetModel(
    id = id, amount = amount, description = description, isSpending = isSpending
)

fun BudgetModel.toEntity() = BudgetEntity(
    id = id, amount = amount, description = description, isSpending = isSpending
)
