package com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.plcoding.jetpackcomposepokedex.domain.models.EvolutionChain
import com.plcoding.jetpackcomposepokedex.domain.models.EvolvesFromSpecies
import com.plcoding.jetpackcomposepokedex.domain.models.FlavorTextEntry
import com.plcoding.jetpackcomposepokedex.domain.models.Genera
import com.plcoding.jetpackcomposepokedex.domain.models.Generation
import com.plcoding.jetpackcomposepokedex.domain.models.GrowthRate
import com.plcoding.jetpackcomposepokedex.domain.models.Habitat
import com.plcoding.jetpackcomposepokedex.domain.models.Names
import com.plcoding.jetpackcomposepokedex.domain.models.PalParkEncounter
import com.plcoding.jetpackcomposepokedex.domain.models.PokedexNumber
import com.plcoding.jetpackcomposepokedex.domain.models.Shape
import com.plcoding.jetpackcomposepokedex.domain.models.Varieties

@Keep
internal data class SearchPokemonResponseDTO(
    @SerializedName("base_happiness") val baseHappiness: Int,
    @SerializedName("capture_rate") val captureRate: Int,
    @SerializedName("color") val color: Int,
    @SerializedName("egg_groups") val eggGroup: Int,
    @SerializedName("evolution_chain") val evolutionChain: EvolutionChain,
    @SerializedName("evolves_from_species") val evolvesFromSpecies: EvolvesFromSpecies?,
    @SerializedName("flavor_text_entries") val flavorTextEntry: FlavorTextEntry,
    @SerializedName("form_description") val formsDescription: List<Any>,
    @SerializedName("forms_switchable") val formsSwitchable: Boolean,
    @SerializedName("gender_rate") val genderRate: Int,
    @SerializedName("genera") val genera: Genera,
    @SerializedName("generation") val generation: Generation,
    @SerializedName("growth_rate") val growthRate: GrowthRate,
    @SerializedName("habitat") val habitat: Habitat,
    @SerializedName("has_gender_differences") val hasGenderDifferences: Boolean,
    @SerializedName("hatch_counter") val hatchCounter: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("is_baby") val isBaby: Boolean,
    @SerializedName("is_legendary") val isLegendary: Boolean,
    @SerializedName("is_mythical") val isMythical: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: Names,
    @SerializedName("order") val order: Int,
    @SerializedName("pal_park_encounter") val palParkEncounter: PalParkEncounter,
    @SerializedName("pokedex_number") val pokedexNumber: PokedexNumber,
    @SerializedName("shape") val shape: Shape,
    @SerializedName("varieties") val varieties: Varieties,
)
