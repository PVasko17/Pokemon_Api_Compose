package p.vasko.pokemon.compose.domain.entity

data class PokemonItemDetails(
    val id: Int,
    val name: String,
    val cost: Double,
    val flingPower: Int,
    val attributes: List<PokemonItemAttribute>,
    val categories: PokemonItemCategory,
    val effects: List<PokemonItemEffect>,
    val imageUrl: String
)
