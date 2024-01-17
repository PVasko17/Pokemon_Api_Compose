package p.vasko.pokemon.compose.data.network

import p.vasko.pokemon.compose.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("item")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonListResponse

    @GET("item/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String)
}