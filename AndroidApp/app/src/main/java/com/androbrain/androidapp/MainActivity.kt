package com.androbrain.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androbrain.androidapp.data.repository.BudgetModel
import com.androbrain.androidapp.ui.dashboard.BudgetCard
import com.androbrain.androidapp.ui.dashboard.DashboardViewModel
import com.androbrain.androidapp.ui.dashboard.edit.EditBudgetDialog
import com.androbrain.androidapp.ui.theme.AndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: DashboardViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAppTheme {
                val state by viewModel.state.collectAsState()
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
    }
}
