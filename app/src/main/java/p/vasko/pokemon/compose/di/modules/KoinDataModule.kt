package p.vasko.pokemon.compose.di.modules

import android.content.Context
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import p.vasko.pokemon.compose.data.database.AppDatabase
import p.vasko.pokemon.compose.data.network.ApiFactory

@Module
@ComponentScan("p.vasko.pokemon.compose")
class DataModule {
    @Single
    fun apiService() = ApiFactory().apiService

    @Single
    fun pokemonListDao(context: Context) = AppDatabase.getInstance(context).getPokemonItemsListDao()
}