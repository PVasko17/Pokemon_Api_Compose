package p.vasko.pokemon.compose.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStoreImpl
import p.vasko.pokemon.compose.data.network.ApiFactory
import p.vasko.pokemon.compose.data.repository.PokemonRepositoryImpl
import p.vasko.pokemon.compose.di.annotation.ApplicationScope
import p.vasko.pokemon.compose.domain.repository.PokemonRepository

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

    @ApplicationScope
    @Binds
    fun bindPokemonNetworkDataStore(impl: PokemonNetworkDataStoreImpl): PokemonNetworkDataStore

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService() = ApiFactory().apiService
    }
}