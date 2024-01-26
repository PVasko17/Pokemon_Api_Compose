package p.vasko.pokemon.compose.data.dataStores

import p.vasko.pokemon.compose.data.database.PokemonItemsListDao
import p.vasko.pokemon.compose.data.database.entities.PokemonListItemDbModel

class PokemonDatabaseDataStoreImpl(
    private val itemsListDao: PokemonItemsListDao,
) : PokemonDatabaseDataStore {
    override fun updatePokemonList(page: Int): List<PokemonListItemDbModel> =
        itemsListDao.getPokemonItems(PAGE_SIZE, calculateOffsetFromPage(page))

    override suspend fun insertPokemonItems(list: List<PokemonListItemDbModel>) {
        itemsListDao.insertPokemonItems(list)
    }
}