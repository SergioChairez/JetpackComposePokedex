package com.plcoding.jetpackcomposepokedex.data.datasource.remote

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.Quality
import com.aallam.openai.api.image.Style
import com.aallam.openai.api.model.ModelId
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.OpenAIService
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.PokemonApiService
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.asDomain
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemoteDataSource
import com.plcoding.jetpackcomposepokedex.domain.datasource.remote.RemotePokemonDataSource
import com.plcoding.jetpackcomposepokedex.domain.models.GenerationList
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonListModel
import com.plcoding.jetpackcomposepokedex.domain.models.PokemonModel
import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import javax.inject.Inject

internal class RemotePokemonDatasourceImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService,
    private val remoteDataSource: RemoteDataSource,
    private val openAIService: OpenAIService
) : RemotePokemonDataSource {
    override suspend fun fetchPokemonList(
        limit: Int,
        offset: Int
    ): ResultValue<PokemonListModel> {
        return remoteDataSource.call {
            pokemonApiService.fetchPokemonList(
                limit = limit,
                offset = offset
            ).asDomain()
        }
    }

    override suspend fun fetchPokemonInfo(
        pokemonName: String
    ): ResultValue<PokemonModel> {
        return remoteDataSource.call {
            pokemonApiService.fetchPokemonInfo(
                name = pokemonName
            ).asDomain()
        }
    }

    override suspend fun fetchGenerationList(
        limit: Int,
        offset: Int
    ): ResultValue<GenerationList> {
        return remoteDataSource.call {
            pokemonApiService.fetchGenerationList(
                limit = limit,
                offset = offset
            ).asDomain()
        }
    }

    override suspend fun fetchGenerationVersion(id: Int): ResultValue<List<VersionGroup>> {
        return remoteDataSource.call {
            pokemonApiService.fetchGenerationVersion(
                id = id,
            ).asDomain()
        }
    }


    override suspend fun searchPokemon(name: String): ResultValue<PokemonModel> {
        return remoteDataSource.call {
            pokemonApiService.searchPokemon(
                name = name,
            ).asDomain()
        }
    }

    override suspend fun getPokemon(name: String, type: String, description: String):
            ResultValue<Pair<String, String>> {
        return remoteDataSource.call {
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.User,
                        content = "Create a description of a Pokemon with" +
                                " this name: $name and this type: $type"
                    )
                )
            )

            val imageCreationRequest = ImageCreation(
                prompt = "Create an image of a Pokemon with" +
                        " this name: $name and this type: $type and this" +
                        " description $description in a location that" +
                        " corresponds to its type in pokemon style",
                n = 1,
                model = ModelId("dall-e-2"),
                quality = Quality("hd"),
                style = Style.Vivid,
                size = ImageSize.is1024x1024
            )

            val creation = openAIService.openAI.imageURL(imageCreationRequest)
            val imageUrl = creation.first().url

            val completion: ChatCompletion = openAIService.openAI.chatCompletion(chatCompletionRequest)
            val chatContent = completion.choices.first().message.content!!

            Pair(imageUrl, chatContent)
        }
    }

}