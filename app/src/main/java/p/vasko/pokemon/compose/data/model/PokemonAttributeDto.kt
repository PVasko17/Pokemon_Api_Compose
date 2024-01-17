package p.vasko.pokemon.compose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonAttributeDto(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("url") @Expose val detailsUrl: String
)
