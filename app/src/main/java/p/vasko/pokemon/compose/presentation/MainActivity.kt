package p.vasko.pokemon.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import p.vasko.pokemon.compose.navigation.AppNavGraph
import p.vasko.pokemon.compose.navigation.rememberNavigationState
import p.vasko.pokemon.compose.presentation.pokemonDetails.PokemonItemDetailsScreen
import p.vasko.pokemon.compose.presentation.pokemonList.PokemonListScreen
import p.vasko.pokemon.compose.presentation.ui.theme.PokemonApiComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationState = rememberNavigationState()

            PokemonApiComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(
                        navHostController = navigationState.navHostController,
                        listScreenContent = {
                            PokemonListScreen {
                                navigationState.navigateToItemDetails(it.name)
                            }
                        },
                        listItemDetailsScreenContent = {
                            PokemonItemDetailsScreen(listItem = it) {
                                navigationState.navHostController.popBackStack()
                            }
                        })
                }
            }
        }
    }
}