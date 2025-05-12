package com.androbrain.crosscompileapp.data.mapping

import com.androbrain.crosscompileapp.data.datasource.BudgetEntity
import com.androbrain.crosscompileapp.data.repository.BudgetModel

fun BudgetEntity.toModel() = BudgetModel(
    id = id, amount = amount, description = description, isSpending = isSpending
)

fun BudgetModel.toEntity() = BudgetEntity(
    id = id, amount = amount, description = description, isSpending = isSpending
)
