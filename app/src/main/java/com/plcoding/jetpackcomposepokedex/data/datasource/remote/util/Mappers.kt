package com.plcoding.jetpackcomposepokedex.data.datasource.remote.util

import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchGenerationResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchPokemonListModelResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchPokemonResponseDTO
import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonEntry
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import kotlin.random.Random

internal fun FetchPokemonListModelResponseDTO.asDomain(): PokemonListModel =
    PokemonListModel(
        count = count,
        previous = previous,
        next = next,
        results = results
    )

internal fun FetchPokemonListModelResponseDTO.toPokemonEntity(): List<PokemonEntity>{
    return results.map { result ->
        PokemonEntity(
            pokemonName = result.name,
            imageUrl = result.url,
            number = Random.nextInt( 1, 2000 )
        )
    }
}

internal fun PokemonEntity.toPokemonEntry(): PokemonEntry {
    return PokemonEntry(
        pokemonName = pokemonName,
        imageUrl = imageUrl,
        number = number
    )
}

internal fun FetchPokemonResponseDTO.asDomain(): PokemonModel =
    PokemonModel(
        abilities = abilities,
        base_experience = baseExperience,
        forms = forms,
        game_indices = gameIndices,
        height = height,
        held_items = heldItems,
        id = id,
        is_default = isDefault,
        location_area_encounters = locationAreaEncounters,
        moves = moves,
        name = name,
        order = order,
        past_abilities = pastAbilities,
        past_types = pastTypes,
        species = species,
        sprites = sprites,
        stats = stats,
        types = types,
        weight = weight,
    )

internal fun FetchGenerationResponseDTO.asDomain(): GenerationList =
    GenerationList(
        count = count,
        next = next,
        previous = previous,
        results = results
    )