package com.androbrain.crosscompileapp.ui.dashboard.edit

import com.androbrain.crosscompileapp.data.repository.BudgetModel

data class EditBudgetState(
    val budget: BudgetModel = BudgetModel(amount = 0, description = "", isSpending = false),
)
