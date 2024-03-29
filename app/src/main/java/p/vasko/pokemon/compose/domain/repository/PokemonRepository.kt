package p.vasko.pokemon.compose.domain.repository

import kotlinx.coroutines.flow.StateFlow
import p.vasko.pokemon.compose.domain.entity.PokemonItemDetails
import p.vasko.pokemon.compose.domain.entity.PokemonListItem

interface PokemonRepository {
    fun pokemonList(): StateFlow<List<PokemonListItem>>

    suspend fun updatePokemonList(page: Int)

    fun getDetails(listItem: String): StateFlow<PokemonItemDetails?>

}