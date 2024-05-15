package com.bayma.dicemanager.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dice_manager.core_design_system.ui.theme.DiceManagerTheme
import com.bayma.dicemanager.R

@Composable
fun DiceComponent(
    onClick: (Int) -> Unit,
    diceIndex: Int,
) {
    when (diceIndex) {
        0 -> DiceItem(diceIndex, R.string.dice_d4_title, R.drawable.dice_d4, onClick)
        1 -> DiceItem(diceIndex, R.string.dice_d6_title, R.drawable.dice_d6, onClick)
        2 -> DiceItem(diceIndex, R.string.dice_d8_title, R.drawable.dice_d8, onClick)
        3 -> DiceItem(diceIndex, R.string.dice_d12_title, R.drawable.dice_d12, onClick)
        4 -> DiceItem(diceIndex, R.string.dice_d20_title, R.drawable.dice_d20, onClick)
    }
}

@Composable
fun DiceItem(
    @DrawableRes diceIndex: Int,
    @StringRes diceDescription: Int,
    diceImage: Int,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp, 16.dp)
            .clickable { onClick(diceIndex) },
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        val painter = painterResource(id = diceImage)
        Image(
            modifier = Modifier.size(64.dp),
            painter = painter,
            contentDescription = "Dice D4",
            alignment = Alignment.BottomEnd
        )
        Text(
            text = stringResource(id = diceDescription),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceManagerTheme {
        DiceComponent({}, 2)
    }
}