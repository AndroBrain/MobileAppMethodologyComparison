package com.androbrain.crosscompileapp.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androbrain.crosscompileapp.data.repository.BudgetModel
import com.androbrain.crosscompileapp.data.repository.BudgetRepository
import com.androbrain.crosscompileapp.ui.toAmount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val budgetRepository: BudgetRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    init {
        observeBudgets()
        observeBalance()
    }

    fun deleteBudget(budget: BudgetModel) {
        viewModelScope.launch { budgetRepository.delete(budget) }
    }

    private fun observeBudgets() {
        budgetRepository.getAll().onEach { budgets ->
            _state.update { state -> state.copy(budgets = budgets.reversed()) }
        }.launchIn(viewModelScope)
    }

    private fun observeBalance() {
        budgetRepository.getAll().onEach { budgets ->
            val balance = budgets.sumOf { budget ->
                if (budget.isSpending) {
                    -1
                } else {
                    1
                } * budget.amount
            }
            _state.update { state -> state.copy(balance = balance.toAmount()) }
        }.launchIn(viewModelScope)
    }
}
