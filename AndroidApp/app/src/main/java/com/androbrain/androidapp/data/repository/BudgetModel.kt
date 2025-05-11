package com.androbrain.androidapp.data.repository

data class BudgetModel(
    val id: Int,
    val amount: Long,
    val description: String,
    val isSpending: Boolean,
)
