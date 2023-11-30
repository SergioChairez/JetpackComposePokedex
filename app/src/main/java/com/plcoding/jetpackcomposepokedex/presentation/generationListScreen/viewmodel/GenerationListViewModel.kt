package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.domain.models.Generation
import com.plcoding.jetpackcomposepokedex.domain.useCases.FetchGenerationListUseCase
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerationListViewModel @Inject constructor(
    private val fetchGenerationListUseCase: FetchGenerationListUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val contentError: Boolean = false,
        val generationList: List<Generation> = listOf(),
        val isLoading: Boolean = true,
        val errorMessage: String = ""
    )

    init {
        loadGenerationList()
    }

    fun loadGenerationList() {
        viewModelScope.launch {
            when(val result = fetchGenerationListUseCase.invoke(limit = 0, offset = 0)) {
                is ResultValue.Success -> {
                    _uiState.update {
                        it.copy(
                            contentError = false,
                            generationList = result.data.results.map { res ->
                                Generation(
                                    res.name,
                                    res.url
                                )
                            },
                            isLoading = false
                        )
                    }
                }
                is ResultValue.Loading -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is ResultValue.Error -> {
                    _uiState.update {
                        it.copy(
                            contentError = true,
                            errorMessage = result.exception.message.toString(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}