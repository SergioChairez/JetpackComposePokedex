package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.plcoding.jetpackcomposepokedex.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        var showBottomSheet: Boolean = false,
        var name: String? = "",
        var type: String? = "",
        val isLoaded: Boolean = false,
        val onStart: Boolean = true,
        val onError: Boolean = false,
        val response: String? = "",
        val url: String = ""
    )

    @OptIn(BetaOpenAI::class)
    fun createPokemonDescription() {
        viewModelScope.launch {
            val openAI = OpenAI(Constants.CHAT_GPT_API_KEY)
            val currentState = _uiState.value

            try {
                val chatCompletionRequest = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(
                        ChatMessage(
                            role = ChatRole.User,
                            content = "Create a description of a Pokemon with this " +
                                    "name: ${currentState.name} and this type: ${currentState.type}"
                        )
                    )
                )

                val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
                val response = completion.choices.first().message?.content

                _uiState.value = currentState.copy(
                    isLoaded = true,
                    onStart = false,
                    response = response ?: "No response"
                )

            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoaded = false,
                    onStart = false,
                    onError = true,
                    response = "Error: ${e.message ?: ""}"
                )
            }

        }

    }

    @OptIn(BetaOpenAI::class)
    fun createPokemonImage() {
        viewModelScope.launch {
            val openAI = OpenAI(Constants.CHAT_GPT_API_KEY)
            val currentState = _uiState.value

            try {
                val imageGenerationRequest = ImageCreation(
                    prompt = "Create an image of a Pokemon with this " +
                            "name: ${currentState.name} and this type: ${currentState.type} " +
                            "and this description ${currentState.response}"
                )

                val imageGeneration = openAI.imageURL(imageGenerationRequest)
                val response = imageGeneration[0].url

                _uiState.value = currentState.copy(
                    isLoaded = true,
                    onStart = false,
                    url = response
                )

            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoaded = false,
                    onStart = false,
                    onError = true,
                    response = "Error: ${e.message ?: ""}"
                )
            }
        }
    }

    fun showBottomSheet() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(showBottomSheet = true)
    }

    fun hideBottomSheet() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(showBottomSheet = false)
    }

}