package p.vasko.pokemon.compose.presentation.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import p.vasko.pokemon.compose.presentation.pokemonList.viewmodel.PokemonListViewModel
import p.vasko.pokemon.compose.presentation.views.PokemonScreenProgressIndicator

@Composable
fun PokemonListScreen(
    onPokemonClickListener: (PokemonListItem) -> Unit
) {

    val viewModel: PokemonListViewModel = koinViewModel()

    val screenState = viewModel.screenState.collectAsState(initial = ListScreenState.Initial)

    PokemonListContent(
        viewModel = viewModel,
        screenState = screenState,
        onPokemonClickListener = onPokemonClickListener
    )
}

@Composable
private fun PokemonListContent(
    viewModel: PokemonListViewModel,
    screenState: State<ListScreenState>,
    onPokemonClickListener: (PokemonListItem) -> Unit,
) {
    when (val currentState = screenState.value) {
        is ListScreenState.Content -> PokemonList(
            viewModel = viewModel,
            list = currentState.list,
            nextPageLoading = currentState.isNextPageLoading,
            onPokemonClickListener = onPokemonClickListener
        )

        ListScreenState.Initial -> Unit
        ListScreenState.Loading -> {
            PokemonScreenProgressIndicator()
        }
    }
}

@Composable
private fun PokemonList(
    viewModel: PokemonListViewModel,
    list: List<PokemonListItem>,
    nextPageLoading: Boolean,
    onPokemonClickListener: (PokemonListItem) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = list, key = { it.name }) {
            ListItem(item = it, onPokemonClickListener = onPokemonClickListener)
        }

        item {
            if (nextPageLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(all = 16.dp)
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                }
            } else {
                SideEffect {
                    viewModel.loadNextPage()
                }
            }
        }
    }
}

@Composable
private fun ListItem(item: PokemonListItem, onPokemonClickListener: (PokemonListItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onPokemonClickListener(item) },
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "Pokemon image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = item.name, color = Color.Black, modifier = Modifier.weight(1f))
            Image(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Open details screen")
        }
    }
}