package com.bayma.dicemanager.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bayma.dicemanager.viewmodels.DiceViewModel

@Composable
fun DiceHistoryComponent(
    modifier: Modifier,
    viewModel: DiceViewModel
) {
    val diceState = viewModel.diceState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(diceState.value.resultHistory) {
            Column {
                Text(text = "Dice: ${it.diceType} - Result: ${it.diceRollResult}")
            }
        }

    }
}