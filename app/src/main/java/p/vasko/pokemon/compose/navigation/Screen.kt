package p.vasko.pokemon.compose.navigation

import android.net.Uri
import p.vasko.pokemon.compose.domain.entity.PokemonListItem

sealed class Screen(
    val route: String
) {
    object PokemonItemsList: Screen(ROUTE_LIST_ITEMS)

    object ItemDetails: Screen(ROUTE_ITEM_DETAILS) {
        private const val ROUTE_FOR_ARGS = "item_details"

        fun getRouteWithArgs(listItem: String): String {
            return "$ROUTE_FOR_ARGS/${listItem.encode()}"
        }
    }

    companion object {

        const val KEY_ITEM_NAME = "item_name"

        const val ROUTE_LIST_ITEMS = "list"
        const val ROUTE_ITEM_DETAILS = "item_details/{$KEY_ITEM_NAME}"
    }
}

fun String.encode(): String = Uri.encode(this)