@file:OptIn(ExperimentalMaterial3Api::class)

package com.bayma.dicemanager.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bayma.dicemanager.R
import com.bayma.dicemanager.viewmodels.DiceViewModel

@Composable
fun DiceQuantityComponent(
    modifier: Modifier,
    onAddQuantityClicked: () -> Unit,
    onSubQuantityClicked: () -> Unit,
    viewModel: DiceViewModel = viewModel(),
) {
    val addPainterIcon = painterResource(id = R.drawable.add_icon)
    val subPainterIcon = painterResource(id = R.drawable.remove_icon)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dice Quantity")
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAddQuantityClicked() }
            ) {
                Icon(painter = addPainterIcon, contentDescription = "add dice quantity")
            }
            TextField(
                modifier = Modifier.width(100.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,

                    ),
                value = "1",
                onValueChange = {},
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)

            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAddQuantityClicked() },
            ) {
                Icon(painter = subPainterIcon, contentDescription = "sub dice quantity")
            }
        }
    }
}

