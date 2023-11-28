package com.plcoding.jetpackcomposepokedex.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PokemonEntity::class,
        GenerationEntity::class
               ],
    version = 2
)
abstract class PokedexDataBase: RoomDatabase() {

    abstract val dao: PokedexDao
}