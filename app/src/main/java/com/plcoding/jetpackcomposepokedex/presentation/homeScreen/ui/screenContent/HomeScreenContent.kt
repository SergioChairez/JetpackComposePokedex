package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.screenContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.util.BottomModalSheet
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    navController: NavController,
    scope: CoroutineScope,
    uiState: HomeScreenViewModel.UiState,
    viewModel: HomeScreenViewModel
) {

    val sheetState = rememberModalBottomSheetState()

    if (uiState.onStart) {
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
                    uiState,
                    viewModel
                )
            }
        }
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Generating Image...",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    textAlign = MaterialTheme.typography.titleLarge.textAlign
                )
            )
        }
    }

    if (uiState.isLoaded) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiState.pokemonData.first)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                },
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = uiState.pokemonData.second,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    textAlign = TextAlign.Justify
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(
                        "home_screen"
                    )
                }
            ) {
                Text(
                    text = "Try again!",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        textAlign = MaterialTheme.typography.titleLarge.textAlign
                    )
                )
            }
        }
    }
    
    if (uiState.onError) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = uiState.errorMessage,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    textAlign = MaterialTheme.typography.titleLarge.textAlign
                ))
        }
    }
}