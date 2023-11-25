package com.plcoding.jetpackcomposepokedex.presentation.generationlist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.presentation.generationlist.ui.screenContent.GenerationListScreenContent
import com.plcoding.jetpackcomposepokedex.presentation.generationlist.viewmodel.GenerationListViewModel

@Composable
fun GenerationListScreen(
    viewModel: GenerationListViewModel = hiltViewModel(),
    navController: NavController,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            GenerationListScreenContent(
                viewModel = viewModel,
                generations = viewModel.generationPagingFlow.flow.collectAsLazyPagingItems(),
                navController = navController
            )
        }
    }
}