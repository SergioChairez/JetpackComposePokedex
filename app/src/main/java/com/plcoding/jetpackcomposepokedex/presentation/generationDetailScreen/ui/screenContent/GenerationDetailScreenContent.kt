package com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.ui.screenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.ui.generationVersionItem.GenerationVersionItem
import com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.viewmodel.GenerationDetailViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.RetrySection

@Composable
fun GenerationDetailScreenContent(
    uiState: GenerationDetailViewModel.UiState,
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
                LazyHorizontalGrid(
                    rows = GridCells.Adaptive(50.dp),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    items(uiState.generationDetail.size) { index ->
                        val generationVersion = uiState.generationDetail[index]
                        GenerationVersionItem(
                            version = generationVersion
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GenerationDetailScreenContentPreview() {
    GenerationDetailScreenContent(
        uiState = GenerationDetailViewModel.UiState(),
        navController = rememberNavController(),
        onRetry = {}
    )
}