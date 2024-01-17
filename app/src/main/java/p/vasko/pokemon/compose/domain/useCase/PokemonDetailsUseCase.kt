package p.vasko.pokemon.compose.domain.useCase

import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(listItem: String) = repository.getDetails(listItem)
}