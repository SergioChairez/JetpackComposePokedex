package com.plcoding.jetpackcomposepokedex.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RetrySection (
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp) )
        Button(
            onClick = { onRetry() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            shape = ButtonDefaults.shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(
                text = "Retry",
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                    lineHeight = MaterialTheme.typography.labelLarge.lineHeight,
                    color = MaterialTheme.colorScheme.surface
                )
            )
        }
    }
}