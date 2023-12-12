package com.plcoding.jetpackcomposepokedex.presentation.searchScreen.ui.searchScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.presentation.searchScreen.ui.searchScreenContent.SearchScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.searchScreen.viewmodel.SearchPokemonViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexTopAppBar

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchPokemonViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PokedexTopAppBar(
                drawerState = null,
                navController = navController,
                onSearch = {
                    searchText = it
                    viewModel.searchPokemon(searchText)
                }
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            SearchScreenContent(
                uiState = uiState,
                navController = navController,
            )
        }
    }
}