package p.vasko.pokemon.compose.domain.useCase

import p.vasko.pokemon.compose.domain.repository.PokemonRepository

class UpdatePokemonListUseCase(private val repository: PokemonRepository) {
    operator fun invoke(page: Int) = repository.updatePokemonList(page)
}