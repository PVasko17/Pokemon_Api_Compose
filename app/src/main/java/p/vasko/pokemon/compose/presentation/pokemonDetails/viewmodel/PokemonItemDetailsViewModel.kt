package p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.map
import p.vasko.pokemon.compose.domain.useCase.PokemonDetailsUseCase
import p.vasko.pokemon.compose.presentation.pokemonDetails.DetailsScreenState

class PokemonItemDetailsViewModel(
    pokemonListItem: String,
    pokemonDetailsUseCase: PokemonDetailsUseCase,
) : ViewModel() {

    val screenState = pokemonDetailsUseCase(pokemonListItem)
        .map {
            if (it != null) {
                DetailsScreenState.Content(
                    listItem = pokemonListItem,
                    details = it
                ) as DetailsScreenState
            } else {
                DetailsScreenState.Loading
            }
        }
}