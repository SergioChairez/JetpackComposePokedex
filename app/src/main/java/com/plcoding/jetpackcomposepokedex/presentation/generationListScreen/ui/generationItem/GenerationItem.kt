package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.ui.generationItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.domain.models.Generation
import java.util.Locale

@Composable
fun GenerationItem(
    generation: Generation,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val parts = generation.name.split("-")

    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    "generation_detail_screen/${generation.name}"
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            1.dp
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "${parts[0].replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                }  ${parts[1].uppercase()}",
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    textAlign = MaterialTheme.typography.titleMedium.textAlign
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewGenerationItem() {
    GenerationItem(
        generation = Generation("Generation X", url = ""),
        navController = rememberNavController()
    )
}