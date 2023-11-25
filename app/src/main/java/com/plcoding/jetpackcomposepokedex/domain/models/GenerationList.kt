package com.plcoding.jetpackcomposepokedex.domain.models

data class GenerationList(
     val count: Int,
     val next: String,
     val previous: String,
     val results: List<Result>,
)
