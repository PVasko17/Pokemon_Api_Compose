package p.vasko.pokemon.compose.di.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonDatabaseDataStoreImpl
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStore
import p.vasko.pokemon.compose.data.dataStores.PokemonNetworkDataStoreImpl
import p.vasko.pokemon.compose.data.database.AppDatabase
import p.vasko.pokemon.compose.data.database.PokemonItemsListDao
import p.vasko.pokemon.compose.data.network.ApiFactory
import p.vasko.pokemon.compose.data.repository.PokemonRepositoryImpl
import p.vasko.pokemon.compose.domain.repository.PokemonRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

    @Binds
    abstract fun bindPokemonNetworkDataStore(impl: PokemonNetworkDataStoreImpl): PokemonNetworkDataStore

    @Binds
    abstract fun bindPokemonDatabaseDataStore(impl: PokemonDatabaseDataStoreImpl): PokemonDatabaseDataStore

    companion object {
        @Provides
        fun provideApiService() = ApiFactory().apiService

        @Provides
        fun providePokemonItemsDao(application: Application): PokemonItemsListDao =
            AppDatabase.getInstance(application).getPokemonItemsListDao()
    }
}