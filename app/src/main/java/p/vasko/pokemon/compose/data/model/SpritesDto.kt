package p.vasko.pokemon.compose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpritesDto(
    @SerializedName("default") @Expose val default: String
)
