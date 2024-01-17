package p.vasko.pokemon.compose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("cost") @Expose val cost: Double,
    @SerializedName("fling_power") @Expose val flingPower: Int,
    @SerializedName("attributes") @Expose val attributes: List<PokemonAttributeDto>,
    @SerializedName("category") @Expose val categories: List<PokemonCategoryDto>,
    @SerializedName("effect_entries") @Expose val effects: List<PokemonEffectDto>
)
