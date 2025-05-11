package com.androbrain.androidapp.data.repository

import androidx.room.PrimaryKey

data class BudgetModel(
    val id: Int,
    val amount: Int,
    val description: String,
    val isSpending: Boolean,
)
