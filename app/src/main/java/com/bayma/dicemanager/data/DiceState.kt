package com.bayma.dicemanager.data

data class DiceState(
    val diceRollResult: Int,
    val hasResult: Boolean = false,
    val additionalValue: Int = 0,
    val diceQuantity: Int = 1,
    val resultHistory: List<DiceResult> = emptyList()
)

data class DiceResult(
    val diceType: Int,
    val diceRollResult: Int
)