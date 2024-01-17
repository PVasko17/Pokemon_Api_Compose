package p.vasko.pokemon.compose.data.dataStores

import p.vasko.pokemon.compose.data.model.PokemonDetailsResponse
import p.vasko.pokemon.compose.data.model.PokemonListResponse

interface PokemonNetworkDataStore {
    suspend fun updatePokemonList(page: Int): PokemonListResponse

    suspend fun getPokemonDetails(name: String): PokemonDetailsResponse
}