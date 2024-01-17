package p.vasko.pokemon.compose.presentation.pokemonDetails

import p.vasko.pokemon.compose.domain.entity.PokemonItemDetails
import p.vasko.pokemon.compose.domain.entity.PokemonListItem

sealed class DetailsScreenState {
    object Initial: DetailsScreenState()

    object Loading: DetailsScreenState()

    data class Content(val listItem: String, val details: PokemonItemDetails): DetailsScreenState()
}