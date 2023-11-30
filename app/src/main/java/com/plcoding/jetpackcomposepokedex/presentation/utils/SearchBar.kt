package com.plcoding.jetpackcomposepokedex.presentation.utils

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search Pokemon",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    val interactionSource = remember { MutableInteractionSource() }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
            color = MaterialTheme.colorScheme.onSurface
        )
        ,
        modifier = modifier
            .focusable(interactionSource = interactionSource),
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

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        hint = "Search",
        onSearch = { /* handle search here */ }
    )
}
