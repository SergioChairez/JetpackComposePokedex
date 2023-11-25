package com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.plcoding.jetpackcomposepokedex.domain.models.Result

@Keep
internal data class FetchPokemonListModelResponseDTO (
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: Int,
    @SerializedName("results") val results: List<Result>,
)