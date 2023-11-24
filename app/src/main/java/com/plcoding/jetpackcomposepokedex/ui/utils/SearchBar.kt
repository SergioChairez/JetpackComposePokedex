package com.plcoding.jetpackcomposepokedex.ui.utils

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
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
        textStyle = TextStyle(color = Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .focusable(interactionSource = interactionSource),
        placeholder = {
            Text(
                text = hint,
                color = Color.LightGray
            )
        }
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
