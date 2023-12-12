package com.plcoding.jetpackcomposepokedex.util

import androidx.compose.ui.graphics.Color
import com.plcoding.jetpackcomposepokedex.domain.models.Stats
import com.plcoding.jetpackcomposepokedex.domain.models.Types
import com.plcoding.jetpackcomposepokedex.presentation.theme.AtkColor
import com.plcoding.jetpackcomposepokedex.presentation.theme.DefColor
import com.plcoding.jetpackcomposepokedex.presentation.theme.HPColor
import com.plcoding.jetpackcomposepokedex.presentation.theme.SpAtkColor
import com.plcoding.jetpackcomposepokedex.presentation.theme.SpDefColor
import com.plcoding.jetpackcomposepokedex.presentation.theme.SpdColor
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeBug
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeDark
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeDragon
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeElectric
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeFairy
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeFighting
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeFire
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeFlying
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeGhost
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeGrass
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeGround
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeIce
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeNormal
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypePoison
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypePsychic
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeRock
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeSteel
import com.plcoding.jetpackcomposepokedex.presentation.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type: Types): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stats): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stats): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}