package com.plcoding.jetpackcomposepokedex.presentation.pokemonlist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.presentation.pokemonlist.ui.screenContent.PokemonListScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.pokemonlist.viewmodel.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.SearchBar

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface (
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
       Column {
           Spacer(modifier = Modifier.height(20.dp))
           Image(
               painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
               contentDescription = "Pokemon",
               modifier = Modifier
                   .fillMaxWidth()
                   .align(CenterHorizontally)
           )
           SearchBar(
               hint = "Search...",
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp)
           ) {
               viewModel.searchPokemonList(it)
           }
           Spacer(modifier = Modifier.height(16.dp))
           PokemonListScreenContent(navController = navController)
       }
    }
}