package com.bayma.dicemanager.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bayma.dicemanager.data.DiceResult
import com.bayma.dicemanager.data.DiceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DiceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DiceState(6))
    val diceState: StateFlow<DiceState> = _uiState.asStateFlow()

    fun getDiceValue(diceIndex: Int) {
        _uiState.update { currentState ->
            val diceRollResult = calculateRollResultByIndex(diceIndex)
            val updatedHistory = listOf(
                *currentState.resultHistory.toTypedArray(),
                DiceResult(diceIndex, diceRollResult)
            )
            currentState.copy(
                diceRollResult = diceRollResult,
                hasResult = true,
                resultHistory = updatedHistory,
            )
        }
        Log.d("DiceViewModel", "Dice value: ${_uiState.value.resultHistory}")
    }

    fun resetDiceValue() {
        _uiState.update { currentState ->
            currentState.copy(
                diceRollResult = 0,
                hasResult = false,
            )
        }
    }

    private fun calculateRollResultByIndex(index: Int): Int {
        return when (index) {
            0 -> (1..4).random()
            1 -> (1..6).random()
            2 -> (1..8).random()
            3 -> (1..12).random()
            4 -> (1..20).random()
            else -> 0
        }
    }
}