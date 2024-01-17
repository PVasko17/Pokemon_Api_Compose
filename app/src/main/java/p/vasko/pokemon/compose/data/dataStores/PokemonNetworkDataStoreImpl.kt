package p.vasko.pokemon.compose.data.dataStores

import p.vasko.pokemon.compose.data.model.PokemonDetailsResponse
import p.vasko.pokemon.compose.data.model.PokemonListResponse
import p.vasko.pokemon.compose.data.network.PokemonApi
import javax.inject.Inject

class PokemonNetworkDataStoreImpl @Inject constructor(
    private val apiService: PokemonApi
): PokemonNetworkDataStore {
    override suspend fun updatePokemonList(page: Int): PokemonListResponse {
        val offset = page * PAGE_SIZE
        return apiService.getPokemonList(offset, PAGE_SIZE)
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetailsResponse {
        return apiService.getPokemonDetails(name)
    }
}