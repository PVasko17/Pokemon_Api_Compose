package p.vasko.pokemon.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateToItemDetails(listItem: String) {
        navHostController.navigate(Screen.ItemDetails.getRouteWithArgs(listItem))
    }
}

@Composable
fun rememberNavigationState(navHostController: NavHostController = rememberNavController()): NavigationState = remember {
    NavigationState(navHostController)
}