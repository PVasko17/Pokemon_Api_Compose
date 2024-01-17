package p.vasko.pokemon.compose.di

import dagger.BindsInstance
import dagger.Subcomponent
import p.vasko.pokemon.compose.di.modules.PokemonItemViewModelModule
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.presentation.ViewModelFactory

@Subcomponent(
    modules = [
        PokemonItemViewModelModule::class
    ]
)
interface PokemonItemDetailsComponent {
    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance listItem: String
        ): PokemonItemDetailsComponent
    }
}