package p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import p.vasko.pokemon.compose.domain.useCase.PokemonDetailsUseCase
import p.vasko.pokemon.compose.presentation.pokemonDetails.DetailsScreenState

@KoinViewModel
class PokemonItemDetailsViewModel(
    @InjectedParam pokemonListItem: String,
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