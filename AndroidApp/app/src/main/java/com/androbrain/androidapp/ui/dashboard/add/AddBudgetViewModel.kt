package com.androbrain.androidapp.ui.dashboard.add

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
class AddBudgetViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AddBudgetState())
    val state = _state.asStateFlow()

    fun resetState() {
        _state.update { AddBudgetState() }
    }

    fun changeIsSpending(isSpending: Boolean) {
        _state.update { state -> state.copy(isSpending = isSpending) }
    }

    fun changeDescription(description: String) {
        _state.update { state -> state.copy(description = description) }
    }

    fun changeAmount(amount: String) {
        try {
            _state.update { state -> state.copy(amount = (amount.toDouble() * 100).toLong()) }
        } catch (e: NumberFormatException) {
            Log.e("Format", e.message.toString())
        }
    }

    fun addBudget() {
        val currentState = state.value
        viewModelScope.launch {
            budgetRepository.insert(
                BudgetModel(
                    amount = currentState.amount,
                    description = currentState.description,
                    isSpending = currentState.isSpending,
                )
            )
        }
    }
}
