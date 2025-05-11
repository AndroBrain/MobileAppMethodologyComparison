package com.androbrain.androidapp.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androbrain.androidapp.data.repository.BudgetModel
import com.androbrain.androidapp.ui.toAmount

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier,
    budget: BudgetModel,
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${if (budget.isSpending) "-" else "+"} ${budget.amount.toAmount()}"
            )
            if (budget.description.isNotBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(text = budget.description)
            }
        }
    }
}