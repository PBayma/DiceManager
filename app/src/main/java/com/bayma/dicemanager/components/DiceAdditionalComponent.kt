package com.bayma.dicemanager.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dice_manager.core_design_system.ui.theme.DiceManagerTheme
import com.bayma.dicemanager.R


@Composable
fun DiceAdditionalComponent(
    modifier: Modifier,
    title: String,
    initialValue: Int,
    onTextChanged: (String) -> Unit,
    minimumValue: Int? = null,
) {
    var value by remember { mutableIntStateOf(initialValue) }

    val addPainterIcon = painterResource(id = R.drawable.add_icon)
    val subPainterIcon = painterResource(id = R.drawable.remove_icon)

    Column(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        value += 1
                        onTextChanged(value.toString())
                    }
            ) {
                Icon(painter = addPainterIcon, contentDescription = "add dice quantity")
            }
            TextField(
                modifier = Modifier
                    .width(100.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Number,
                ),
                value = TextFieldValue(
                    text = value.toString(),
                    selection = TextRange(value.toString().length)
                ),
                onValueChange = {
                    if (it.text.matches(Regex("\\d+"))) {
                        if (minimumValue != null && it.text.toInt() < minimumValue) return@TextField
                        value = it.text.toInt()
                        onTextChanged(it.text)
                    } else if (it.text.isEmpty()) {
                        value = minimumValue ?: 0
                        onTextChanged("0")
                    }
                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)

            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        if (minimumValue != null) {
                            if (value == minimumValue) return@clickable
                        }
                        value -= 1
                        onTextChanged(value.toString())
                    },
            ) {
                Icon(painter = subPainterIcon, contentDescription = "sub dice quantity")
            }
        }
    }
}

@Preview
@Composable
fun DiceAdditionalPreview() {
    DiceManagerTheme {
        DiceAdditionalComponent(
            modifier = Modifier,
            initialValue = 1,
            title = "Bonus Additional",
            onTextChanged = { },
            minimumValue = 0
        )
    }
}