package com.androbrain.androidapp.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetEntity(
    @PrimaryKey val id: Int,
    val amount: Int,
    val description: String,
    val isSpending: Boolean,
)
