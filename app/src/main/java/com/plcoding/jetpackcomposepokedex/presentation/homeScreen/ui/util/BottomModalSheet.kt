package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomModalSheet(
    scope: CoroutineScope,
    sheetState: SheetState,
    uiState: HomeScreenViewModel.UiState,
    viewModel: HomeScreenViewModel
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var name by remember {
        mutableStateOf("")
    }

    var type by remember {
        mutableStateOf("")
    }

    ModalBottomSheet(
        modifier = Modifier.height(screenHeight * 0.7f),
        onDismissRequest = {
            viewModel.hideBottomSheet()
        },
        sheetState = sheetState
    ) {
        Box (
            modifier = Modifier.padding(10.dp),
            contentAlignment = Alignment.Center
        )
        {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter the stats and type of your pokemon!",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        textAlign = MaterialTheme.typography.titleLarge.textAlign
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = name,
                        onValueChange = {
                            name = it
                            uiState.name = it
                        },
                        label = {
                            Text(text = "Name")
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = type,
                        onValueChange = {
                            type = it
                            uiState.type = it
                        },
                        label = {
                            Text(text = "Type")
                        }
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {
                        scope.launch (Dispatchers.IO) { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                viewModel.hideBottomSheet()
                                viewModel.createPokemon()
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Apply parameters",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            textAlign = MaterialTheme.typography.titleLarge.textAlign
                        )
                    )
                }
            }
        }
    }
}