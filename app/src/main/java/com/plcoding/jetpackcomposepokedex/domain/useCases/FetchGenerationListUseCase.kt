package com.plcoding.jetpackcomposepokedex.domain.useCases

import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

class FetchGenerationListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(limit: Int, offset: Int): ResultValue<GenerationList> {
        return repository.fetchGenerationList(limit, offset)
    }

}