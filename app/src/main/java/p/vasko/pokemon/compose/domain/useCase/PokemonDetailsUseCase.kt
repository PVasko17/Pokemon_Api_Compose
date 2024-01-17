package p.vasko.pokemon.compose.domain.useCase

import p.vasko.pokemon.compose.domain.repository.PokemonRepository

class PokemonDetailsUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(name: String) = repository.getDetails(name)
}