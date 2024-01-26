package p.vasko.pokemon.compose.domain.useCase

import org.koin.core.annotation.Factory
import p.vasko.pokemon.compose.domain.repository.PokemonRepository

@Factory
class PokemonDetailsUseCase(
    private val repository: PokemonRepository,
) {
    operator fun invoke(listItem: String) = repository.getDetails(listItem)
}