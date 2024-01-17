package p.vasko.pokemon.compose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonEffectDto(
    @SerializedName("effect") @Expose val effect: String,
    @SerializedName("short_effect") @Expose val shortEffect: String,
    @SerializedName("language") @Expose val language: ItemLanguageDto
)
