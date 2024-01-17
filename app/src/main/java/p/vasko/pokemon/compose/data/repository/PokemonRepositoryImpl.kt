package p.vasko.pokemon.compose.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStore
import p.vasko.pokemon.compose.data.mapper.PokemonMapper
import p.vasko.pokemon.compose.domain.entity.PokemonDetails
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val networkDataStore: PokemonNetworkDataStore,
    private val mapper: PokemonMapper,
) : PokemonRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val pokemonUpdateListEvents = MutableSharedFlow<Int>(replay = 1)

    private val _pokemonList = mutableListOf<PokemonListItem>()
    private val pokemonList: List<PokemonListItem>
        get() = _pokemonList.toList()

    private val pokemonListFlow = flow {
        pokemonUpdateListEvents.emit(0)
        pokemonUpdateListEvents.collect {
            val result = networkDataStore.updatePokemonList(it)
            _pokemonList.addAll(mapper.mapListDtoToEntity(result))
            emit(pokemonList)
        }
    }
        .retry {
            delay(RETRY_TIMEOUT)
            true
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

    override suspend fun getDetails(name: String): StateFlow<PokemonDetails?> = flow {
        val result = networkDataStore.getPokemonDetails(name)
        emit(mapper.mapDetailsDtoToEntity(result))
    }.retry {
        delay(RETRY_TIMEOUT)
        true
    }.stateIn(
        coroutineScope,
        SharingStarted.Lazily,
        null
    )

    companion object {
        const val RETRY_TIMEOUT = 3000L
    }
}