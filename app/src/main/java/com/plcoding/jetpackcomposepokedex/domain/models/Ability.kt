package com.plcoding.jetpackcomposepokedex.domain.models

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)