package com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.plcoding.jetpackcomposepokedex.domain.models.Abilities
import com.plcoding.jetpackcomposepokedex.domain.models.Form
import com.plcoding.jetpackcomposepokedex.domain.models.GameIndice
import com.plcoding.jetpackcomposepokedex.domain.models.HeldItem
import com.plcoding.jetpackcomposepokedex.domain.models.Move
import com.plcoding.jetpackcomposepokedex.domain.models.Species
import com.plcoding.jetpackcomposepokedex.domain.models.Sprites
import com.plcoding.jetpackcomposepokedex.domain.models.Stats
import com.plcoding.jetpackcomposepokedex.domain.models.Types

@Keep
internal data class FetchPokemonResponseDTO(
    @SerializedName("abilities") val abilities: List<Abilities>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("forms") val forms: List<Form>,
    @SerializedName("game_indices") val gameIndices: List<GameIndice>,
    @SerializedName("height") val height: Int,
    @SerializedName("held_items") val heldItems: List<HeldItem>,
    @SerializedName("id") val id: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<Move>,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("past_abilities") val pastAbilities: List<Any>,
    @SerializedName("past_types") val pastTypes: List<Any>,
    @SerializedName("species") val species: Species,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("weight") val weight: Int
)
