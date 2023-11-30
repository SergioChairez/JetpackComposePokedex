package com.plcoding.jetpackcomposepokedex.domain.models

data class Abilities(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)