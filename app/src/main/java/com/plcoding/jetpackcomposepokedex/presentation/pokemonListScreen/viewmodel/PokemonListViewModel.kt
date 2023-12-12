package com.plcoding.jetpackcomposepokedex.presentation.pokemonListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.plcoding.jetpackcomposepokedex.data.datasource.local.PokemonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pager: Pager<Int, PokemonEntity>,
): ViewModel() {
    val pokemonListPagingFlow = pager.flow.cachedIn(viewModelScope)

}