package p.vasko.pokemon.compose.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import p.vasko.pokemon.compose.di.annotation.ViewModelKey
import p.vasko.pokemon.compose.presentation.pokemonList.viewmodel.PokemonListViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    @Binds
    fun bindPokemonListViewModel(viewModel: PokemonListViewModel): ViewModel
}