package com.androbrain.androidapp.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androbrain.androidapp.data.repository.BudgetModel
import com.androbrain.androidapp.data.repository.BudgetRepository
import com.androbrain.androidapp.ui.toAmount
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
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
