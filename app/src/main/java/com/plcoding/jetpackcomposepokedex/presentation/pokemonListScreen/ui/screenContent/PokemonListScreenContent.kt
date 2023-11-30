package com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.ui.screenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.ui.pokemonItem.PokemonItem
import com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.viewmodel.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.RetrySection

@Composable
fun PokemonListScreenContent(
    pokemonList: LazyPagingItems<PokemonEntity>,
    navController: NavController,
    onRetry: () -> Unit,
    viewModel: PokemonListViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (pokemonList.loadState.refresh) {
            is LoadState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(120.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(pokemonList.itemCount) { index ->
                        val pokemon = pokemonList[index]
                        if (pokemon != null) {
                            PokemonItem(
                                entry = pokemon,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                    item {
                        if (pokemonList.loadState.append is LoadState.Loading &&
                            !pokemonList.loadState.append.endOfPaginationReached
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.scale(0.2f)
                            )
                        }
                    }
                }
            }

            is LoadState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    RetrySection(
                        error = (pokemonList.loadState.refresh as LoadState.Error)
                            .error
                            .message
                            .toString(),
                        onRetry = {
                            onRetry()
                        }
                    )
                }
            }
        }
    }
}