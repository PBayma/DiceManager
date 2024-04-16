package com.bayma.dicemanager.data

data class DiceState(
    val diceRollResult: Int,
    val hasResult: Boolean = false,
    val resultHistory: List<DiceResult> = emptyList()
)

data class DiceResult(
    val diceType: Int,
    val diceRollResult: Int
)