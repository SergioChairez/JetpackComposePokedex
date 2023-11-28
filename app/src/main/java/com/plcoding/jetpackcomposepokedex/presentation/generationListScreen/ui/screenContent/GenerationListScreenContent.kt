package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.screenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.toGeneration
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.generationItem.GenerationItem
import com.plcoding.jetpackcomposepokedex.presentation.utils.RetrySection

@Composable
fun GenerationListScreenContent(
    generations: LazyPagingItems<GenerationEntity>,
    navController: NavController,
    onRetry: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        when (generations.loadState.refresh) {
            is LoadState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(generations.itemCount) { index ->
                        val generation = generations[index]
                        if (generation != null) {
                            GenerationItem(
                                generation = generation.toGeneration(),
                                navController = navController,
                            )
                        }
                    }
                    item {
                        if ( generations.loadState.append is LoadState.Loading &&
                            !generations.loadState.append.endOfPaginationReached ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.scale(0.2f)
                            )
                        }
                    }
                }
            }
            is LoadState.Error -> {
                RetrySection(
                    error = (generations.loadState.refresh as LoadState.Error )
                        .error
                        .message
                        .toString(),
                    onRetry = {
                        onRetry()
                    }
                )
            }
        }
    }

}