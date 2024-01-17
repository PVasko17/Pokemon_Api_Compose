package p.vasko.pokemon.compose.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import p.vasko.pokemon.compose.di.annotation.ViewModelKey
import p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel.PokemonItemDetailsViewModel

@Module
interface PokemonItemViewModelModule {
    @IntoMap
    @ViewModelKey(PokemonItemDetailsViewModel::class)
    @Binds
    fun bindPokemonDetailsViewModel(viewModel: PokemonItemDetailsViewModel): ViewModel
}