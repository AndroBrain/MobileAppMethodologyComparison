package com.androbrain.crosscompileapp.ui.dashboard

import com.androbrain.crosscompileapp.data.repository.BudgetModel

data class DashboardState(
    val balance: String = "",
    val budgets: List<BudgetModel> = emptyList(),
)
