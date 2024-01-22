package p.vasko.pokemon.compose.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStore
import p.vasko.pokemon.compose.data.mapper.PokemonMapper
import p.vasko.pokemon.compose.domain.entity.PokemonItemDetails
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val networkDataStore: PokemonNetworkDataStore,
    private val dbDataStore: PokemonDatabaseDataStore,
    private val mapper: PokemonMapper,
) : PokemonRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val pokemonUpdateListEvents = MutableSharedFlow<Int>(replay = 1)

    private val _pokemonList = mutableListOf<PokemonListItem>()
    private val pokemonList: List<PokemonListItem>
        get() = _pokemonList.toList()

    private val pokemonListFlow = flow {
        pokemonUpdateListEvents.emit(0)
        pokemonUpdateListEvents.collect { page ->
            val currentPageList = fetchListData(page)
            _pokemonList.addAll(currentPageList)
            emit(pokemonList)
        }
    }
        .retry {
            delay(RETRY_TIMEOUT)
            true
        }

    private suspend fun fetchListData(page: Int): List<PokemonListItem> {
        val dbValues = mapper.mapListDbModelToEntity(dbDataStore.updatePokemonList(page))
        return dbValues.ifEmpty {
            val result = networkDataStore.updatePokemonList(page)
            dbDataStore.insertPokemonItems(mapper.mapListDtoToDbModel(result))
            mapper.mapListDtoToEntity(result)
        }
    }

    override fun pokemonList(): StateFlow<List<PokemonListItem>> = pokemonListFlow
        .catch {
            emit(pokemonList)
        }
        .stateIn(
            coroutineScope,
            SharingStarted.Lazily,
            pokemonList
        )

    override suspend fun updatePokemonList(page: Int) {
        pokemonUpdateListEvents.emit(page)
    }

    override fun getDetails(listItem: String): StateFlow<PokemonItemDetails?> = flow {
        val result = networkDataStore.getPokemonDetails(listItem)
        emit(mapper.mapDetailsDtoToEntity(result))
    }.stateIn(
        coroutineScope,
        SharingStarted.Lazily,
        null
    )

    companion object {
        const val RETRY_TIMEOUT = 3000L
    }
}