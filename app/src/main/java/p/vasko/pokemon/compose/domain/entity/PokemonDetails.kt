package p.vasko.pokemon.compose.domain.entity

data class PokemonDetails(
    val id: Int,
    val name: String,
    val cost: Double,
    val flingPower: Int,
    val attributes: List<PokemonAttribute>,
    val categories: List<PokemonCategory>,
    val effects: List<PokemonEffect>
)
