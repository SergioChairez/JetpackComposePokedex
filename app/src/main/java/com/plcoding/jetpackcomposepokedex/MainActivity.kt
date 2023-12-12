package com.plcoding.jetpackcomposepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.ui.screen.GenerationDetailScreen
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.screen.GenerationListScreen
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.screen.HomeScreen
import com.plcoding.jetpackcomposepokedex.presentation.pokemonDetailScreen.ui.pokemonDetailScreen.PokemonDetailScreen
import com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.ui.screen.PokemonListScreen
import com.plcoding.jetpackcomposepokedex.presentation.searchScreen.ui.searchScreen.SearchScreen
import com.plcoding.jetpackcomposepokedex.presentation.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                NavHost(
                    navController = navController,
                    startDestination = "home_screen"
                )
                {
                    composable("home_screen") {
                        HomeScreen(
                            drawerState = drawerState,
                            navController = navController
                        )
                    }
                    composable("generation_list_screen") {
                        GenerationListScreen(
                            drawerState = drawerState,
                            navController = navController
                        )
                    }
                    composable(
                        "generation_detail_screen/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val generationId = remember {
                            it.arguments?.getInt("id")
                        }
                        if (generationId != null) {
                            GenerationDetailScreen(
                                id = generationId,
                                drawerState = drawerState,
                                navController = navController
                            )
                        }
                    }
                    composable("pokemon_list_screen") {
                        PokemonListScreen(
                            drawerState = drawerState,
                            navController = navController
                        )
                    }
                    composable("search_screen") {
                        SearchScreen(navController = navController)
                    }
                    composable(
                        "pokemon_detail_screen/{dominantColor}/{pokemonName}",
                        arguments = listOf(
                            navArgument("dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        PokemonDetailScreen(
                            drawerState = drawerState,
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
