package com.androbrain.androidapp.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Long,
    val description: String,
    val isSpending: Boolean,
)
