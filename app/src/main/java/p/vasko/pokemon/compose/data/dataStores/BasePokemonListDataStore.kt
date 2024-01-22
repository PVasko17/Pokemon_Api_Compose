package p.vasko.pokemon.compose.data.dataStores

interface BasePokemonListDataStore {
    fun calculateOffsetFromPage(page: Int) = page * PAGE_SIZE + 1
}