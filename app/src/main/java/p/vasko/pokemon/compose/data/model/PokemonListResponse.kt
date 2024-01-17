package p.vasko.pokemon.compose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") @Expose val count: Int,
    @SerializedName("next") @Expose val nextPageUrl: String,
    @SerializedName("previous") @Expose val prevPageUrl: String,
    @SerializedName("results") @Expose val results: List<PokemonListItemDto>
)
