package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.screenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.generationItem.GenerationItem
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel.GenerationListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.RetrySection

@Composable
fun GenerationListScreenContent(
    uiState: GenerationListViewModel.UiState,
    navController: NavController,
    onRetry: () -> Unit
) {

    if(uiState.contentError) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RetrySection(
                error = uiState.errorMessage,
                onRetry = {
                    onRetry()
                }
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            if (uiState.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(uiState.generationList.size) { index ->
                        val generation = uiState.generationList[index]
                        GenerationItem(
                            generation = generation,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}