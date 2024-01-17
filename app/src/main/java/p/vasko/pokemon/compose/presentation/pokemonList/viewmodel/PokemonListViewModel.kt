package p.vasko.pokemon.compose.presentation.pokemonList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import p.vasko.pokemon.compose.domain.useCase.PokemonListFlowUseCase
import p.vasko.pokemon.compose.domain.useCase.UpdatePokemonListUseCase
import p.vasko.pokemon.compose.extention.mergeWith
import p.vasko.pokemon.compose.presentation.pokemonList.ListScreenState
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    pokemonListFlowUseCase: PokemonListFlowUseCase,
    private val updatePokemonListUseCase: UpdatePokemonListUseCase
): ViewModel() {

    private val _nextPage = MutableStateFlow(0)

    private val pokemonListFlow = pokemonListFlowUseCase()

    private val loadNextPageFlow = MutableSharedFlow<ListScreenState>()

    val screenState = pokemonListFlow
        .filter { it.isNotEmpty() }
        .map {
            _nextPage.value = _nextPage.value + 1
            ListScreenState.Content(it, false) as ListScreenState
        }
        .onStart { emit(ListScreenState.Loading) }
        .mergeWith(loadNextPageFlow)

    fun loadNextPage() {
        viewModelScope.launch {
            loadNextPageFlow.emit(
                ListScreenState.Content(
                    pokemonListFlow.value,
                    true
                )
            )
            updatePokemonListUseCase(_nextPage.value)
        }
    }
}