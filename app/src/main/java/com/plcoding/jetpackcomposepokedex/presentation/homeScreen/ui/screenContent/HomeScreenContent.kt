package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.screenContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.util.BottomModalSheet
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    navController: NavController,
    uiState: HomeScreenViewModel.UiState,
    viewModel: HomeScreenViewModel
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (uiState.isStart) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Welcome to your Pokedex!",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    textAlign = MaterialTheme.typography.titleLarge.textAlign
                )
            )
            Image(
                painter = painterResource(id = R.drawable.pokemon_welcome_image),
                contentDescription = null
            )
            Text(
                text = "Enter the stats and characteristics to create a new Pokemon!",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    textAlign = MaterialTheme.typography.titleLarge.textAlign
                )
            )
            Button(
                onClick = { viewModel.showBottomSheet() },
            ) {
                Text(
                    text = "Generate Pokemon",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        textAlign = MaterialTheme.typography.titleLarge.textAlign
                    )
                )
            }

            if (uiState.showBottomSheet) {
                BottomModalSheet(
                    scope,
                    sheetState,
                    viewModel
                )
            }
        }
    }

    if (uiState.isLoading) {
        Box (modifier = Modifier.fillMaxSize()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                ,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}