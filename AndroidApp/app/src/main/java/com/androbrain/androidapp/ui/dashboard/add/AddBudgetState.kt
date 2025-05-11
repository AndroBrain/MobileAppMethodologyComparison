package com.androbrain.androidapp.ui.dashboard.add

data class AddBudgetState(
    val isSpending: Boolean = false,
    val amount: Long = 0,
    val description: String = "",
)
