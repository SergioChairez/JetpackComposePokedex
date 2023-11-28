package com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.ui.pokemonItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.viewmodel.PokemonListViewModel

@Composable
fun PokemonItem(
    entry: PokemonEntity,
    navController: NavController,
    viewModel: PokemonListViewModel
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            }
    ) {
        Column {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(viewModel.url(entry.imageUrl))
                    .build(),
                contentDescription = entry.pokemonName,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                },
                onSuccess = {
                    val drawable = it.result.drawable
                    viewModel.calcDominantColor(drawable) { color->
                        dominantColor = color
                    }
                }
            )
            Text(
                text = viewModel.formatName(entry.pokemonName),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    textAlign = MaterialTheme.typography.titleMedium.textAlign
                )
            )
        }
    }
}