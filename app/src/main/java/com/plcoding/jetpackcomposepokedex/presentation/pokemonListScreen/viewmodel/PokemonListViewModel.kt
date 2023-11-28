package com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.palette.graphics.Palette
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pager: Pager<Int, PokemonEntity>,
): ViewModel() {
    val pokemonListPagingFlow = pager.flow.cachedIn(viewModelScope)

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        viewModelScope.launch {
            Palette.from(bmp).generate { palette ->
                palette?.dominantSwatch?.rgb?.let { colorValue ->
                    onFinish(Color(colorValue))
                }
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

    fun formatName(pokemonName: String): String {
        return pokemonName.split("-").joinToString(" ") { it.replaceFirstChar { firstChar ->
            if (firstChar.isLowerCase()) firstChar.titlecase(
                Locale.ROOT
            ) else firstChar.toString()
        } }
    }
}