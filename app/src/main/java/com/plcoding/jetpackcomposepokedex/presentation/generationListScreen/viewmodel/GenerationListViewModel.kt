package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.palette.graphics.Palette
import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.util.toGeneration
import com.plcoding.jetpackcomposepokedex.domain.useCases.FetchGenerationListUseCase
import com.plcoding.jetpackcomposepokedex.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerationListViewModel @Inject constructor(
    pager: Pager<Int, GenerationEntity>,
    private val useCase: FetchGenerationListUseCase
): ViewModel() {

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var generationPagingFlow = pager

    fun loadGenerationsPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = useCase.invoke(10, 0)) {
                is ResultValue.Success -> {
                    generationPagingFlow
                        .flow
                        .map { pagingData ->
                            pagingData.map {
                                it.toGeneration()
                            }
                        }
                        .cachedIn(viewModelScope)
                }
                is ResultValue.Error -> {
                    loadError.value = result.exception.message!!
                    isLoading.value = false
                }

                else -> {}
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        viewModelScope.launch {
            Palette.from(bmp).generate { palette ->
                palette?.dominantSwatch?.rgb?.let { colorValue ->
                    onFinish(Color(colorValue))
                }
            }
        }
    }
}