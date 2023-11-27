package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.generationItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.domain.models.Generation
import com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel.GenerationListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.theme.RobotoCondensed

@Composable
fun GenerationItem(
    generation: Generation,
    modifier: Modifier = Modifier,
    viewModel: GenerationListViewModel = hiltViewModel(),
    navController: NavController
) {

    val defaultDominantColor = MaterialTheme.colorScheme.surface
    val dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    val parts = generation.name.split("-")

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(125.dp)
            .padding(5.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "generation_detail_screen/${generation.name}"
                )
            }
    ) {
        Column {
            Text(
                text = "${parts[0].uppercase()}  ${parts[1].uppercase()}",
                fontFamily = RobotoCondensed,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
        }
    }
}