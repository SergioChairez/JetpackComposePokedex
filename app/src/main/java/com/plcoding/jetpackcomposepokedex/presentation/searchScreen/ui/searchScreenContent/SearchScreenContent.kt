package com.plcoding.jetpackcomposepokedex.presentation.searchScreen.ui.searchScreenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.presentation.searchScreen.viewmodel.SearchPokemonViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.RetrySection

@Composable
fun SearchScreenContent(
    uiState: SearchPokemonViewModel.UiState,
    navController: NavController,
) {

    if(uiState.searchError) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RetrySection(
                error = uiState.errorMessage,
                onRetry = {}
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                item(uiState.pokemon) {
                    val pokemon = uiState.pokemon

                }
            }
        }
    }

}