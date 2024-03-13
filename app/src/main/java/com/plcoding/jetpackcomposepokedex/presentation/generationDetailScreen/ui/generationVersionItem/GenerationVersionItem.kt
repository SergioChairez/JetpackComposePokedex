package com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.ui.generationVersionItem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup
import com.plcoding.jetpackcomposepokedex.util.formatName
import java.util.Locale

@Composable
fun GenerationVersionItem(
    version: VersionGroup,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .height(50.dp)
        ,
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
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (version.name.contains("-", true)) {
                    formatName(version.name)
                } else {
                    version.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                ,
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
fun GenerationVersionItemPreview() {
    GenerationVersionItem(version = VersionGroup("Red", ""))
}