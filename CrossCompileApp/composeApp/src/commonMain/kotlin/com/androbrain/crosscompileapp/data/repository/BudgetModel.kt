package com.androbrain.crosscompileapp.data.repository

data class BudgetModel(
    val id: Int = 0,
    val amount: Long,
    val description: String,
    val isSpending: Boolean,
)
