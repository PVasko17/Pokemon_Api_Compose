package p.vasko.pokemon.compose.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import p.vasko.pokemon.compose.di.modules.dataModule
import p.vasko.pokemon.compose.di.modules.viewModelModule

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@PokemonApplication)
            // Load modules
            modules(dataModule, viewModelModule)
        }
    }
}