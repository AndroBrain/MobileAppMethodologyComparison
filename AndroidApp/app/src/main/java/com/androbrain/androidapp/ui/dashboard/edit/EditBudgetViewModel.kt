package com.androbrain.androidapp.ui.dashboard.edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androbrain.androidapp.data.repository.BudgetModel
import com.androbrain.androidapp.data.repository.BudgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EditBudgetViewModel @Inject constructor(
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
            Log.e("Format", e.message.toString())
        }
    }

    fun addBudget() {
        viewModelScope.launch { budgetRepository.insert(state.value.budget) }
    }
}
