package p.vasko.pokemon.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.lang.NullPointerException

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    listScreenContent: @Composable () -> Unit,
    listItemDetailsScreenContent: @Composable (String) -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Screen.PokemonItemsList.route) {
        composable(
            route = Screen.PokemonItemsList.route
        ) {
            listScreenContent()
        }

        composable(route = Screen.ItemDetails.route,
            arguments = listOf(
                navArgument(Screen.KEY_ITEM_NAME) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            val itemName = it.arguments?.getString(Screen.KEY_ITEM_NAME) ?: throw NullPointerException("Args is null")
            listItemDetailsScreenContent(itemName)
        }
    }
}