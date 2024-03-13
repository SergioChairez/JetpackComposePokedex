package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.ui.screenContent.HomeScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel.HomeScreenViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexModalSheet
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationBar
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationDrawer
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexTopAppBar

@Composable
fun HomeScreen(
    drawerState: DrawerState,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState().value

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
                    navController = navController
                )
            }
        ) { innerPadding ->
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                HomeScreenContent(
                    navController,
                    uiState,
                    viewModel
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val mockNavController = rememberNavController()

    HomeScreen(
        navController = mockNavController,
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
}
