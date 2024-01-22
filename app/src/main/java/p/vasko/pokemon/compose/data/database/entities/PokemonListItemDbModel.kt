package p.vasko.pokemon.compose.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("pokemon_item")
data class PokemonListItemDbModel(
    @PrimaryKey val name: String,
    val imageUrl: String
)
