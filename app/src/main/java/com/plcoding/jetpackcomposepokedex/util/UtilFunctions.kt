package com.plcoding.jetpackcomposepokedex.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import java.util.Locale

fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
    val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    Palette.from(bmp).generate { palette ->
        palette?.dominantSwatch?.rgb?.let { colorValue ->
            onFinish(Color(colorValue))
        }
    }
}

fun url(imageUrl: String): String {
    val number = if (imageUrl.endsWith("/")) {
        imageUrl.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        imageUrl.takeLastWhile { it.isDigit() }
    }
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
}

fun formatName(string: String): String {
    return string.split("-").joinToString(" ") { it.replaceFirstChar { firstChar ->
        if (firstChar.isLowerCase()) firstChar.titlecase(
            Locale.ROOT
        ) else firstChar.toString()
    } }
}