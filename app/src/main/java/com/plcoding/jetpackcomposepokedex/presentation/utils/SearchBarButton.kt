package com.plcoding.jetpackcomposepokedex.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController

@Composable
fun SearchBarButton(
    modifier: Modifier = Modifier,
    hint: String = "Search Pokemon",
    navController: NavController
) {
    val interactionSource = remember { MutableInteractionSource() }

    TextField(
        value = "",
        onValueChange = {},
        enabled = false,
        maxLines = 1,
        singleLine = true,
        modifier = modifier
            .focusable(interactionSource = interactionSource)
            .clickable { navController.navigate("search_screen") },
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}