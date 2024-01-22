package p.vasko.pokemon.compose.di.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStoreImpl
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStoreImpl
import p.vasko.pokemon.compose.data.database.AppDatabase
import p.vasko.pokemon.compose.data.database.PokemonItemsListDao
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

    @ApplicationScope
    @Binds
    fun bindPokemonDatabaseDataStore(impl: PokemonDatabaseDataStoreImpl): PokemonDatabaseDataStore

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService() = ApiFactory().apiService

        @ApplicationScope
        @Provides
        fun providePokemonItemsDao(application: Application): PokemonItemsListDao =
            AppDatabase.getInstance(application).getPokemonItemsListDao()
    }
}