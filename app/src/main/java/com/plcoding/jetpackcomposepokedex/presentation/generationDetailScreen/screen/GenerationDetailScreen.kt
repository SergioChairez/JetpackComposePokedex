package com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexModalSheet
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationBar
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationDrawer
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexTopAppBar

@Composable
fun GenerationDetailScreen(
    drawerState: DrawerState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    PokedexNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PokedexModalSheet(
                drawerState = drawerState,
                scope = scope,
                navController = navController
            )
        },
    ) {
        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                PokedexTopAppBar(
                    drawerState = drawerState,
                    navController = navController,
                )
            },
            bottomBar = {
                PokedexNavigationBar(
                    navController = navController,
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
            }
        }
    }
}