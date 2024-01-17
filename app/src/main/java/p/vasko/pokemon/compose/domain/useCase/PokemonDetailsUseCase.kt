package p.vasko.pokemon.compose.domain.useCase

import p.vasko.pokemon.compose.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(name: String) = repository.getDetails(name)
}