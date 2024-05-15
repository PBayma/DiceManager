package com.bayma.dicemanager.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dice_manager.core_design_system.ui.theme.DiceManagerTheme
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
            DiceResultComponent(diceType = it.diceType, diceRollResult = it.diceRollResult)
        }

    }
}


@Composable
fun DiceResultComponent(
    diceType: Int,
    diceRollResult: Int
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Text(
            text = "Dice: $diceType",
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Result: $diceRollResult",
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
fun DiceResultPreview() {
    DiceManagerTheme {
        DiceResultComponent(
            diceType = 4,
            diceRollResult = 3
        )
    }
}