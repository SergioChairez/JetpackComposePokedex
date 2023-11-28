package com.plcoding.jetpackcomposepokedex.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val pokemonName: String,
    val imageUrl: String,
    val number: Int,
)
