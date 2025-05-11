package com.androbrain.androidapp.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onDeleteClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${if (budget.isSpending) "-" else "+"} ${budget.amount.toAmount()}"
                )
                if (budget.description.isNotBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(text = budget.description)
                }
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}