package com.bayma.dicemanager.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bayma.dicemanager.viewmodels.DiceViewModel

@Composable
fun DiceCadastrationScreen(
    navController: NavHostController,
    onAddNewButtonClicked: () -> Unit,
    viewModel: DiceViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Ai papai")

    }

}
