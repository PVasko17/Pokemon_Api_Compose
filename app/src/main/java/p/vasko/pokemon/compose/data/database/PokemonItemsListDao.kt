package p.vasko.pokemon.compose.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import p.vasko.pokemon.compose.data.database.entities.PokemonListItemDbModel

@Dao
interface PokemonItemsListDao {
    @Query("SELECT * FROM pokemon_item LIMIT :limit OFFSET :offset")
    fun getPokemonItems(limit: Int, offset: Int): List<PokemonListItemDbModel>

    @Insert
    suspend fun insertPokemonItems(list: List<PokemonListItemDbModel>)
}