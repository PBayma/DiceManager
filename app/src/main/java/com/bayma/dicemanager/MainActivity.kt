package com.bayma.dicemanager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bayma.dicemanager.components.AlertDialogExample
import com.bayma.dicemanager.components.DiceAdditionalComponent
import com.bayma.dicemanager.components.DiceCadastrationScreen
import com.bayma.dicemanager.components.DiceComponent
import com.bayma.dicemanager.components.DiceHistoryComponent
import com.bayma.dicemanager.foundation.DevicePreviews
import com.bayma.dicemanager.ui.theme.DiceManagerTheme
import com.bayma.dicemanager.viewmodels.DiceViewModel

enum class DiceScreens(val title: String) {
    DICES("Dice manager"),
    DICE_CREATION("Dice Creation"),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceManagerApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(currentScreen: DiceScreens, navController: NavHostController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            if (currentScreen != DiceScreens.DICES) {
                IconButton(onClick = {
                    Log.d("DiceManager", "Back button clicked")
                    navController.navigate(DiceScreens.DICES.name)
                }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onPrimary,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        },
        title = {
            Text(currentScreen.title)
        }
    )
}

@Composable
fun DiceManagerApp(
    viewModel: DiceViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = DiceScreens.valueOf(
        backStackEntry?.destination?.route ?: DiceScreens.DICES.name
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(currentScreen = currentScreen, navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DiceScreens.DICES.name,
            modifier = Modifier.padding(innerPadding)

        ) {
            composable(route = DiceScreens.DICES.name) {
                DiceManagerScreen(
                    viewModel = viewModel,
                    onAddNewButtonClicked = {

                    })
            }
            composable(route = DiceScreens.DICE_CREATION.name) {
                DiceCadastrationScreen(
                    navController = navController,
                    onAddNewButtonClicked = {},
                    viewModel = viewModel,
                )

            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiceManagerScreen(
    onAddNewButtonClicked: () -> Unit,
    viewModel: DiceViewModel = viewModel(),
) {
    val diceState = viewModel.diceState.collectAsState()

    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Dices", "History")

    val pagerState = rememberPagerState {
        tabs.size
    }

    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            tabIndex = pagerState.currentPage
        }
    }

    if (diceState.value.hasResult) {
        AlertDialogExample(
            onConfirmation = { viewModel.resetDiceValue() },
            dialogTitle = "Dice Result",
            dialogText = "The result is: ${diceState.value.diceRollResult}",
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier,
            selectedTabIndex = tabIndex
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { index ->
            if (index == 0) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(80.dp),
                        content = {
                            items(6) {
                                DiceComponent(
                                    onClick = { index -> viewModel.getDiceValue(index) },
                                    diceIndex = it
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        DiceAdditionalComponent(
                            modifier = Modifier,
                            title = "Dice Quantity",
                            initialValue = 1,
                            onTextChanged = { viewModel.textChangeDiceQuantityValue(it) },
                            minimumValue = 1
                        )
                        DiceAdditionalComponent(
                            modifier = Modifier,
                            title = "Bonus Additional",
                            initialValue = 0,
                            onTextChanged = { viewModel.textChangeAdditionalValue(it) }
                        )
                    }
                }


            } else {
                DiceHistoryComponent(modifier = Modifier.fillMaxSize(), viewModel = viewModel)
            }
        }

    }


}

@DevicePreviews
@Composable
fun DiceQuantityPreview(
//    @PreviewParameter(IndexPreviewParameter::class) tabIndex: Int
) {
    DiceManagerTheme {
        DiceManagerScreen(onAddNewButtonClicked = {})
    }
}