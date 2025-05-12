package com.androbrain.crosscompileapp.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androbrain.crosscompileapp.data.repository.BudgetModel
import com.androbrain.crosscompileapp.ui.toAmount

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier,
    budget: BudgetModel,
    onEditClick: () -> Unit,
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
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            Spacer(Modifier.width(8.dp))
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}