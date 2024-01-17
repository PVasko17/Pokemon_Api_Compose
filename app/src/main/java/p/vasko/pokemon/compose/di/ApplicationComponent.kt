package p.vasko.pokemon.compose.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import p.vasko.pokemon.compose.di.annotation.ApplicationScope
import p.vasko.pokemon.compose.di.modules.DataModule
import p.vasko.pokemon.compose.di.modules.ViewModelModule
import p.vasko.pokemon.compose.presentation.ViewModelFactory

@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    fun getPokemonItemDetailsComponentFactory(): PokemonItemDetailsComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}