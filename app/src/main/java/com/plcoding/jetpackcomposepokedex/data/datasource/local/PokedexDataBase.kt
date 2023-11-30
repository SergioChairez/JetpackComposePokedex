package com.plcoding.jetpackcomposepokedex.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PokemonEntity::class,
               ],
    version = 3
)
abstract class PokedexDataBase: RoomDatabase() {

    abstract val dao: PokedexDao
}