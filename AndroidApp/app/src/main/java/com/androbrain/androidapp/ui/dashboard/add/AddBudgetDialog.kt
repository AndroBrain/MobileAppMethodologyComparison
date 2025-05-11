package com.androbrain.androidapp.ui.dashboard.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androbrain.androidapp.R

@Composable
fun AddBudgetDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    val viewModel: AddBudgetViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.resetState() }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Text(stringResource(R.string.add_budget_title))
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1F),
                        text = stringResource(R.string.add_budget_is_spending)
                    )
                    Switch(
                        checked = state.isSpending,
                        onCheckedChange = viewModel::changeIsSpending,
                    )
                }
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = String.format(
                        "%.2f",
                        state.amount.toDouble() / 100,
                    ),
                    onValueChange = { viewModel.changeAmount(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = state.description,
                    onValueChange = viewModel::changeDescription,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                viewModel.addBudget()
                onDismissRequest()
            }) {
                Text(stringResource(R.string.add_budget_confirm))
            }
        }
    )
}
