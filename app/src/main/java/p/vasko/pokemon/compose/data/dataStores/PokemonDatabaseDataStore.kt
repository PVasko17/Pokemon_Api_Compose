package p.vasko.pokemon.compose.data.dataStores

import p.vasko.pokemon.compose.data.database.entities.PokemonListItemDbModel

interface PokemonDatabaseDataStore: BasePokemonListDataStore {
    fun updatePokemonList(page: Int): List<PokemonListItemDbModel>

    suspend fun insertPokemonItems(list: List<PokemonListItemDbModel>)

}