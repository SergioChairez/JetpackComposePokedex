package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomModalSheet(
    scope: CoroutineScope,
    sheetState: SheetState,
    viewModel: HomeScreenViewModel
) {
    ModalBottomSheet(
        onDismissRequest = {
            viewModel.hideBottomSheet()
        },
        sheetState = sheetState
    ) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Column {
                Text(
                    text = "Enter the stats and type of your pokemon!",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        textAlign = MaterialTheme.typography.titleLarge.textAlign
                    )
                )
                Button(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                viewModel.hideBottomSheet()
                                viewModel.sendPokemonInfo()
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Apply parameters",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
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