package com.androbrain.crosscompileapp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androbrain.crosscompileapp.data.repository.BudgetModel
import com.androbrain.crosscompileapp.ui.dashboard.BudgetCard
import com.androbrain.crosscompileapp.ui.dashboard.DashboardViewModel
import com.androbrain.crosscompileapp.ui.dashboard.edit.EditBudgetDialog
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: DashboardViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        var currentlyEditedBudget: BudgetModel? by remember { mutableStateOf(null) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = state.balance) }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        currentlyEditedBudget = BudgetModel(
                            amount = 0,
                            description = "",
                            isSpending = false
                        )
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        ) { innerPadding ->
            currentlyEditedBudget?.let { budget ->
                EditBudgetDialog(
                    budget = budget,
                    onDismissRequest = { currentlyEditedBudget = null })
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = innerPadding
            ) {
                items(state.budgets) { budget ->
                    BudgetCard(
                        modifier = Modifier.animateItem(),
                        budget = budget,
                        onEditClick = { currentlyEditedBudget = budget },
                        onDeleteClick = { viewModel.deleteBudget(budget) },
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
