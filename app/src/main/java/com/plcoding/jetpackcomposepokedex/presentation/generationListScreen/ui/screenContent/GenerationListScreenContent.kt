package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.screenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.toGeneration
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.generationItem.GenerationItem
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel.GenerationListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.utils.RetrySection

@Composable
fun GenerationListScreenContent(
    viewModel: GenerationListViewModel = hiltViewModel(),
    generations: LazyPagingItems<GenerationEntity>,
    navController: NavController
) {

    Box(modifier = Modifier.fillMaxHeight()) {
        when (generations.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(generations.itemCount) { index ->
                        val generation = generations[index]
                        if (generation != null) {
                            GenerationItem(
                                generation = generation.toGeneration(),
                                modifier = Modifier,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                    item {
                        if ( generations.loadState.append is LoadState.Loading &&
                            !generations.loadState.append.endOfPaginationReached ) {
                            LinearProgressIndicator()
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
                        viewModel.loadGenerationsPaginated()
                    }
                )
            }
        }
    }

}