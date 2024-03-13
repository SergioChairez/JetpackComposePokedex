package com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.ui.screenContent.GenerationDetailScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.viewmodel.GenerationDetailViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexModalSheet
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationBar
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationDrawer
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexTopAppBar

@Composable
fun GenerationDetailScreen(
    drawerState: DrawerState,
    viewModel: GenerationDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState().value
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val index = navBackStackEntry?.arguments?.getInt("index")
    LaunchedEffect(index) {
        if (index != null) {
            viewModel.loadGenerationDetail(id = index)
        }
    }

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
                GenerationDetailScreenContent(
                    uiState = uiState,
                    navController = navController,
                    onRetry = {
                        if (index != null) {
                            viewModel.loadGenerationDetail(id = index)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun GenerationDetailScreenPreview() {
    GenerationDetailScreen(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        navController = rememberNavController(),
    )
}