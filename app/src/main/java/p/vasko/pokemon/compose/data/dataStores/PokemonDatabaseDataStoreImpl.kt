package p.vasko.pokemon.compose.data.dataStores

import kotlinx.coroutines.flow.Flow
import p.vasko.pokemon.compose.data.database.PokemonItemsListDao
import p.vasko.pokemon.compose.data.database.entities.PokemonListItemDbModel
import javax.inject.Inject

class PokemonDatabaseDataStoreImpl @Inject constructor(
    private val itemsListDao: PokemonItemsListDao
): PokemonDatabaseDataStore {
    override fun updatePokemonList(page: Int): List<PokemonListItemDbModel> = itemsListDao.getPokemonItems(PAGE_SIZE, calculateOffsetFromPage(page))

    override suspend fun insertPokemonItems(list: List<PokemonListItemDbModel>) {
        itemsListDao.insertPokemonItems(list)
    }
}