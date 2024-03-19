package com.plcoding.jetpackcomposepokedex.presentation.homeScreen.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.Quality
import com.aallam.openai.api.image.Style
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

                val imageCreationRequest = ImageCreation(
                    prompt = "Create an image of a Pokemon with this " +
                            "name: ${currentState.name} and this type: ${currentState.type} " +
                            "and this description ${currentState.response} in a location that " +
                            "corresponds to its type"
                    ,
                    n = 1,
                    model = ModelId("dall-e-2"),
                    quality = Quality("hd"),
                    style = Style.Vivid,
                    size = ImageSize.is1024x1024
                )

                val creation = openAI.imageURL(imageCreationRequest)
                val imageResponse = creation.first().url
                Log.i("URL", imageResponse)

                val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
                val completionResponse = completion.choices.first().message.content

                _uiState.value = currentState.copy(
                    isLoaded = true,
                    onStart = false,
                    response = completionResponse ?: "No response",
                    url = imageResponse
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