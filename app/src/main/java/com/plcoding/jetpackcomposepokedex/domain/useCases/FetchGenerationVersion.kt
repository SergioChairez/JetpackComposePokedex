package com.plcoding.jetpackcomposepokedex.domain.useCases

import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

class FetchGenerationVersion @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend fun invoke(id: Int): ResultValue<List<VersionGroup>> {
        return repository.fetchGenerationVersion(id = id)
    }

}