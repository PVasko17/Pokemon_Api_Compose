package p.vasko.pokemon.compose.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import p.vasko.pokemon.compose.di.ApplicationComponent
import p.vasko.pokemon.compose.di.DaggerApplicationComponent

class PokemonApplication : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this@PokemonApplication)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as PokemonApplication).component
}