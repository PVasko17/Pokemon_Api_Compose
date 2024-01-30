package p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import p.vasko.pokemon.compose.domain.useCase.PokemonDetailsUseCase
import p.vasko.pokemon.compose.presentation.pokemonDetails.DetailsScreenState

@HiltViewModel(assistedFactory = PokemonItemDetailsViewModel.PokemonItemDetailsViewModelFactory::class)
class PokemonItemDetailsViewModel @AssistedInject constructor(
    @Assisted pokemonListItem: String,
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

    @AssistedFactory
    interface PokemonItemDetailsViewModelFactory {
        fun create(pokemonListItem: String): PokemonItemDetailsViewModel
    }
}