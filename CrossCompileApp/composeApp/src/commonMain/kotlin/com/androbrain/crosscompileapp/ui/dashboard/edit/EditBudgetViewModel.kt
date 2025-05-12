package com.androbrain.crosscompileapp.ui.dashboard.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androbrain.crosscompileapp.data.repository.BudgetModel
import com.androbrain.crosscompileapp.data.repository.BudgetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditBudgetViewModel(
    private val budgetRepository: BudgetRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(EditBudgetState())
    val state = _state.asStateFlow()

    fun resetState(budget: BudgetModel) {
        _state.update { EditBudgetState(budget = budget) }
    }

    fun changeIsSpending(isSpending: Boolean) {
        _state.update { state -> state.copy(budget = state.budget.copy(isSpending = isSpending)) }
    }

    fun changeDescription(description: String) {
        _state.update { state -> state.copy(budget = state.budget.copy(description = description)) }
    }

    fun changeAmount(amount: String) {
        try {
            _state.update { state -> state.copy(budget = state.budget.copy(amount = (amount.toDouble() * 100).toLong())) }
        } catch (e: NumberFormatException) {
            println(e.message)
        }
    }

    fun addBudget() {
        viewModelScope.launch { budgetRepository.insert(state.value.budget) }
    }
}
