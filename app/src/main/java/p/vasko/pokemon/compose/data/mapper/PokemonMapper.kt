package p.vasko.pokemon.compose.data.mapper

import p.vasko.pokemon.compose.data.model.PokemonDetailsResponse
import p.vasko.pokemon.compose.data.model.PokemonListResponse
import p.vasko.pokemon.compose.domain.entity.PokemonAttribute
import p.vasko.pokemon.compose.domain.entity.PokemonCategory
import p.vasko.pokemon.compose.domain.entity.PokemonDetails
import p.vasko.pokemon.compose.domain.entity.PokemonEffect
import p.vasko.pokemon.compose.domain.entity.PokemonListItem
import javax.inject.Inject

class PokemonMapper @Inject constructor() {
    companion object {
        private const val BASE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items"
        private const val DEFAULT_LANGUAGE_CODE = "en"
    }

    fun mapListDtoToEntity(response: PokemonListResponse): List<PokemonListItem> {
        return response.results.map {
            PokemonListItem(
                name = it.name,
                imageUrl = getImageUrl(it.name)
            )
        }
    }

    fun mapDetailsDtoToEntity(response: PokemonDetailsResponse): PokemonDetails {
        val englishEffects = response.effects.filter { it.language.name == DEFAULT_LANGUAGE_CODE }

        return PokemonDetails(
            id = response.id,
            name = response.name,
            cost = response.cost,
            flingPower = response.flingPower,
            attributes = response.attributes.map {
                PokemonAttribute(
                    name = it.name
                )
            },
            categories = response.categories.map {
                PokemonCategory(
                    name = it.name
                )
            },
            effects = englishEffects.map {
                PokemonEffect(
                    effect = it.effect,
                    shortEffect = it.shortEffect
                )
            }
        )
    }

    private fun getImageUrl(name: String) = "$BASE_IMAGE_URL/$name.png"
}