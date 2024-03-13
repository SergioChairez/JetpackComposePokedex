package com.plcoding.jetpackcomposepokedex.presentation.generationDetailScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup
import com.plcoding.jetpackcomposepokedex.domain.useCases.FetchGenerationVersion
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerationDetailViewModel @Inject constructor(
    private val fetchGenerationVersion: FetchGenerationVersion,
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val contentError: Boolean = false,
        val generationDetail: List<VersionGroup> = listOf(),
        val isLoading: Boolean = true,
        val errorMessage: String = ""
    )

    fun loadGenerationDetail(id: Int) {
        viewModelScope.launch {
            when(val result = fetchGenerationVersion.invoke(id = id)) {
                is ResultValue.Success -> {
                    _uiState.update {
                        it.copy(
                            contentError = false,
                            generationDetail = result.data,
                            isLoading = false
                        )
                    }
                }
                is ResultValue.Loading -> {
                    _uiState.update {
                        it.copy(
                            contentError = false,
                            isLoading = true
                        )
                    }
                }
                is ResultValue.Error -> {
                    _uiState.update {
                        it.copy(
                            contentError = true,
                            errorMessage = result.exception.message.toString()
                        )
                    }
                }
            }
        }
    }

}