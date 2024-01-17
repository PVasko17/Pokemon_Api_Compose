package p.vasko.pokemon.compose.domain.repository

import kotlinx.coroutines.flow.StateFlow
import p.vasko.pokemon.compose.domain.entity.PokemonDetails
import p.vasko.pokemon.compose.domain.entity.PokemonListItem

interface PokemonRepository {
    fun pokemonList(): StateFlow<List<PokemonListItem>>

    suspend fun updatePokemonList(page: Int)

    suspend fun getDetails(name: String): StateFlow<PokemonDetails?>

}