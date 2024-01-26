package p.vasko.pokemon.compose.di.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStoreImpl
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStoreImpl
import p.vasko.pokemon.compose.data.database.AppDatabase
import p.vasko.pokemon.compose.data.mapper.PokemonMapper
import p.vasko.pokemon.compose.data.network.ApiFactory
import p.vasko.pokemon.compose.data.repository.PokemonRepositoryImpl
import p.vasko.pokemon.compose.domain.repository.PokemonRepository
import p.vasko.pokemon.compose.domain.useCase.*

val dataModule = module {
    singleOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
    singleOf(::PokemonNetworkDataStoreImpl) { bind<PokemonNetworkDataStore>() }
    singleOf(::PokemonDatabaseDataStoreImpl) { bind<PokemonDatabaseDataStore>() }
    factory {
        ApiFactory().apiService
    }

    factory {
        AppDatabase.getInstance(androidApplication()).getPokemonItemsListDao()
    }

    factoryOf(::PokemonListFlowUseCase)
    factoryOf(::PokemonDetailsUseCase)
    factoryOf(::UpdatePokemonListUseCase)

    factoryOf(::PokemonMapper)
}