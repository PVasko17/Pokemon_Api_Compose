package p.vasko.pokemon.compose.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel.PokemonItemDetailsViewModel
import p.vasko.pokemon.compose.presentation.pokemonList.viewmodel.PokemonListViewModel

val viewModelModule = module {
//    viewModelOf(::PokemonListViewModel)
//    viewModel { params -> PokemonItemDetailsViewModel(params.get(), get()) }
}