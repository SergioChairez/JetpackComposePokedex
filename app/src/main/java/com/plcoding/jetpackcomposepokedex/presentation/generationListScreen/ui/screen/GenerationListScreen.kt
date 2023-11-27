package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.screenContent.GenerationListScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel.GenerationListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexModalSheet
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexNavigationBar
import com.plcoding.jetpackcomposepokedex.presentation.utils.PokedexTopAppBar

@Composable
fun GenerationListScreen(
    drawerState: DrawerState,
    viewModel: GenerationListViewModel = hiltViewModel(),
    navController: NavController,
) {

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
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
                    GenerationListScreenContent(
                        viewModel = viewModel,
                        generations = viewModel.generationPagingFlow.flow.collectAsLazyPagingItems(),
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGenerationListScreen() {
    // Create a mock ViewModel and NavController for the preview
    val mockViewModel = hiltViewModel<GenerationListViewModel>()
    val mockNavController = rememberNavController()

    // Provide the ViewModel to the screen
    GenerationListScreen(
        viewModel = mockViewModel,
        navController = mockNavController,
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    )
}