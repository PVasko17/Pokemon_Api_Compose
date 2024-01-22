package p.vasko.pokemon.compose.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import p.vasko.pokemon.compose.data.database.entities.PokemonListItemDbModel

@Dao
interface PokemonItemsListDao {
    @Query("SELECT * FROM pokemon_item LIMIT :limit OFFSET :offset")
    fun getPokemonItems(limit: Int, offset: Int): Flow<List<PokemonListItemDbModel>>
}