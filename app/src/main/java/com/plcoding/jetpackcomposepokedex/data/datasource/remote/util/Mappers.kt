package com.plcoding.jetpackcomposepokedex.data.datasource.remote.util

import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchGenerationResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchPokemonListModelResponseDTO
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response.FetchPokemonResponseDTO
import com.plcoding.jetpackcomposepokedex.domain.models.Generation
import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.Pokemon
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel

internal fun FetchPokemonListModelResponseDTO.asDomain(): PokemonListModel =
    PokemonListModel(
        count = count,
        previous = previous,
        next = next,
        results = results
    )

internal fun FetchPokemonResponseDTO.asDomain(): Pokemon =
    Pokemon(
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

internal fun FetchGenerationResponseDTO.toGenerationEntity(): List<GenerationEntity> {
    return results.map { result ->
        GenerationEntity(
            name = result.name,
        )
    }
}

internal fun GenerationEntity.toGeneration(): Generation {
    return Generation(
        name = name
    )
}