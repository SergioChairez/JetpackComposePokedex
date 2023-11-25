package com.plcoding.jetpackcomposepokedex.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenerationEntity(
    @PrimaryKey
    val name: String
)
