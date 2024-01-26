package p.vasko.pokemon.compose.domain.useCase

import org.koin.core.annotation.Factory
import p.vasko.pokemon.compose.domain.repository.PokemonRepository

@Factory
class UpdatePokemonListUseCase(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(page: Int) = repository.updatePokemonList(page)
}