package com.plcoding.jetpackcomposepokedex.presentation.generationListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.plcoding.jetpackcomposepokedex.data.datasource.local.GenerationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenerationListViewModel @Inject constructor(
    pager: Pager<Int, GenerationEntity>,
): ViewModel() {
    val generationPagingFlow = pager.flow.cachedIn(viewModelScope)
}