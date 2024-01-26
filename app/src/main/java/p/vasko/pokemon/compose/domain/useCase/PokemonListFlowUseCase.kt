package p.vasko.pokemon.compose.domain.useCase

import p.vasko.pokemon.compose.domain.repository.PokemonRepository

class PokemonListFlowUseCase(
    private val repository: PokemonRepository,
) {
    operator fun invoke() = repository.pokemonList()
}