package p.vasko.pokemon.compose.presentation.pokemonList

import p.vasko.pokemon.compose.domain.entity.PokemonListItem

sealed class ListScreenState {

    object Initial: ListScreenState()
    object Loading: ListScreenState()

    data class Content(val list: List<PokemonListItem>, val isNextPageLoading: Boolean): ListScreenState()
}