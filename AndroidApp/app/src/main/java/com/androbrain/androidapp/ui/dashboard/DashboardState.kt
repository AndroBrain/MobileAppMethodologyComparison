package com.androbrain.androidapp.ui.dashboard

import com.androbrain.androidapp.data.repository.BudgetModel

data class DashboardState(
    val balance: String = "",
    val budgets: List<BudgetModel> = emptyList(),
)
