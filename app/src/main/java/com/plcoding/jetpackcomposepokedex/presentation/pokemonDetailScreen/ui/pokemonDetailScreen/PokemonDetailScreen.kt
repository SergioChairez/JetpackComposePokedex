package com.plcoding.jetpackcomposepokedex.presentation.pokemonDetailScreen.ui.pokemonDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.presentation.pokemonDetailScreen.ui.pokemonDetailScreenContent.PokemonDetailScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.pokemonDetailScreen.viewmodel.PokemonDetailViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexTopAppBar
import com.plcoding.jetpackcomposepokedex.util.ResultValue

@Composable
fun PokemonDetailScreen(
    drawerState: DrawerState,
    dominantColor: Color,
    pokemonName: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonModelInfo = produceState<ResultValue<PokemonModel>>(initialValue = ResultValue.Loading()) {
        value = viewModel.fetchPokemonInfo(pokemonName)
    }.value
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .padding(bottom = 16.dp)
    ) {
        PokedexTopAppBar(
            navController = navController,
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        )

        when ( pokemonModelInfo ) {
            is ResultValue.Success -> {
                PokemonDetailScreenContent(
                    pokemonModelInfo = pokemonModelInfo.data,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = topPadding + pokemonImageSize / 2f,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        )
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (-20).dp)
                )
            }
            is ResultValue.Error -> {
                Text(
                    text = pokemonModelInfo.exception.message.toString(),
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = topPadding + pokemonImageSize / 2f,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        )
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            is ResultValue.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .padding(
                            top = topPadding + pokemonImageSize / 2f,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        )
                )
            }
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
            .fillMaxSize()
        ) {
            if ( pokemonModelInfo is ResultValue.Success ) {
                pokemonModelInfo.data.sprites.let {
                    AsyncImage(
                        model = it.front_default,
                        contentDescription = pokemonModelInfo.data.name,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .offset(y = topPadding)
                    )
                }
            }
        }
    }
}