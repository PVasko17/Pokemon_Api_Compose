package p.vasko.pokemon.compose.data.dataStores

import org.koin.core.annotation.Single
import p.vasko.pokemon.compose.data.model.PokemonDetailsResponse
import p.vasko.pokemon.compose.data.model.PokemonListResponse
import p.vasko.pokemon.compose.data.network.PokemonApi

@Single
class PokemonNetworkDataStoreImpl(
    private val apiService: PokemonApi,
) : PokemonNetworkDataStore {
    override suspend fun updatePokemonList(page: Int): PokemonListResponse {
        return apiService.getPokemonList(PAGE_SIZE, calculateOffsetFromPage(page))
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetailsResponse {
        return apiService.getPokemonDetails(name)
    }
}