package p.vasko.pokemon.compose.domain.repository

import kotlinx.coroutines.flow.StateFlow
import p.vasko.pokemon.compose.domain.entity.PokemonDetails
import p.vasko.pokemon.compose.domain.entity.PokemonListItem

interface PokemonRepository {
    fun getPokemonList(): StateFlow<List<PokemonListItem>>

    fun updatePokemonList(page: Int)

    fun getDetails(name: String): PokemonDetails

}