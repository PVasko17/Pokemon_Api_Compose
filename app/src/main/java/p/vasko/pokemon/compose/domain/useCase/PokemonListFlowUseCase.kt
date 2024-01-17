package p.vasko.pokemon.compose.domain.useCase

import p.vasko.pokemon.compose.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonListFlowUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke() = repository.pokemonList()
}