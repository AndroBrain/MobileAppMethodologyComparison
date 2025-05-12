package com.androbrain.crosscompileapp.ui.dashboard.edit

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.androbrain.crosscompileapp.data.repository.BudgetModel
import com.androbrain.crosscompileapp.ui.roundToDecimals
import crosscompileapp.composeapp.generated.resources.Res
import crosscompileapp.composeapp.generated.resources.add_budget_title
import crosscompileapp.composeapp.generated.resources.add_confirm
import crosscompileapp.composeapp.generated.resources.amount_label
import crosscompileapp.composeapp.generated.resources.description_label
import crosscompileapp.composeapp.generated.resources.edit_budget_title
import crosscompileapp.composeapp.generated.resources.edit_confirm
import crosscompileapp.composeapp.generated.resources.is_spending
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EditBudgetDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    budget: BudgetModel,
) {
    val viewModel: EditBudgetViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.resetState(budget) }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                stringResource(
                    if (budget.id == 0) {
                        Res.string.add_budget_title
                    } else {
                        Res.string.edit_budget_title
                    }
                )
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1F),
                        text = stringResource(Res.string.is_spending)
                    )
                    Switch(
                        checked = state.budget.isSpending,
                        onCheckedChange = viewModel::changeIsSpending,
                    )
                }
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = (state.budget.amount.toDouble() / 100).roundToDecimals(2).toString(),
                    onValueChange = { viewModel.changeAmount(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(Res.string.amount_label)) }
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = state.budget.description,
                    onValueChange = viewModel::changeDescription,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(Res.string.description_label)) }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                viewModel.addBudget()
                onDismissRequest()
            }) {
                Text(
                    stringResource(
                        if (budget.id == 0) {
                            Res.string.add_confirm
                        } else {
                            Res.string.edit_confirm
                        }
                    )
                )
            }
        }
    )
}
