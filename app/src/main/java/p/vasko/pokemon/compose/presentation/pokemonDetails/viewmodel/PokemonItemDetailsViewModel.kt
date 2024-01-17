package p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.map
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.domain.useCase.PokemonDetailsUseCase
import p.vasko.pokemon.compose.presentation.pokemonDetails.DetailsScreenState
import javax.inject.Inject

class PokemonItemDetailsViewModel @Inject constructor(
    pokemonListItem: String,
    pokemonDetailsUseCase: PokemonDetailsUseCase
): ViewModel() {

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