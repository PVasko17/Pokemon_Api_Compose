package p.vasko.pokemon.compose.presentation.pokemonDetails

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import p.vasko.pokemon.compose.domain.entity.PokemonItemEffect
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.presentation.getApplicationComponent
import p.vasko.pokemon.compose.presentation.pokemonDetails.viewmodel.PokemonItemDetailsViewModel
import p.vasko.pokemon.compose.presentation.views.PokemonScreenProgressIndicator

@Composable
fun PokemonItemDetailsScreen(
    listItem: String,
    onBackPressed: () -> Unit,
) {
    val component = getApplicationComponent()
        .getPokemonItemDetailsComponentFactory()
        .create(listItem)

    val viewModel: PokemonItemDetailsViewModel =
        viewModel(factory = component.getViewModelFactory())
    val screenState = viewModel.screenState.collectAsState(initial = DetailsScreenState.Initial)

    ItemDetailsScreenContent(
        listItem = listItem,
        screenState = screenState,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun ItemDetailsScreenContent(
    listItem: String,
    screenState: State<DetailsScreenState>,
    onBackPressed: () -> Unit,
) {
    when (val currentState = screenState.value) {
        DetailsScreenState.Initial -> Unit
        DetailsScreenState.Loading -> {
            PokemonScreenProgressIndicator()
        }

        is DetailsScreenState.Content -> ItemDetailsContent(
            listItem, currentState, onBackPressed
        )
    }
}

@Composable
private fun ItemDetailsContent(
    listItem: String,
    currentState: DetailsScreenState.Content,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            DetailsTopBar(listItem, onBackPressed)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val itemDetails = currentState.details

            AsyncImage(
                model = itemDetails.imageUrl,
                contentDescription = "${itemDetails.name} icon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(128.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = itemDetails.name)

            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 4.dp
                )
            ) {
                items(items = itemDetails.effects, key = { it.shortEffect.hashCode() }) {
                    EffectListItem(it)
                }
            }
        }

    }
}

@Composable
private fun EffectListItem(itemEffect: PokemonItemEffect) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = itemEffect.shortEffect, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = Html.fromHtml(itemEffect.effect, Html.FROM_HTML_MODE_LEGACY).toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsTopBar(listItem: String, onBackPressed: () -> Unit) {
    TopAppBar(
        title = { Text(text = listItem) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        }
    )
}