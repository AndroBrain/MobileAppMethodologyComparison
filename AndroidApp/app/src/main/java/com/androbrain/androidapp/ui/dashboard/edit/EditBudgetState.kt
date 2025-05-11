package com.androbrain.androidapp.ui.dashboard.edit

import com.androbrain.androidapp.data.repository.BudgetModel

data class EditBudgetState(
    val budget: BudgetModel = BudgetModel(amount = 0, description = "", isSpending = false),
)
