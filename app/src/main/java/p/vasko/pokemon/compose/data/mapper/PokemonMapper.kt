package p.vasko.pokemon.compose.data.mapper

import p.vasko.pokemon.compose.data.database.entities.PokemonListItemDbModel
import p.vasko.pokemon.compose.data.model.PokemonDetailsResponse
import p.vasko.pokemon.compose.data.model.PokemonListResponse
import p.vasko.pokemon.compose.domain.entity.PokemonItemAttribute
import p.vasko.pokemon.compose.domain.entity.PokemonItemCategory
import p.vasko.pokemon.compose.domain.entity.PokemonItemDetails
import p.vasko.pokemon.compose.domain.entity.PokemonItemEffect
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

    fun mapListDtoToDbModel(response: PokemonListResponse): List<PokemonListItemDbModel> {
        return response.results.map {
            PokemonListItemDbModel(
                name = it.name,
                imageUrl = getImageUrl(it.name)
            )
        }
    }

    fun mapListDbModelToEntity(list: List<PokemonListItemDbModel>): List<PokemonListItem> {
        return list.map {
            PokemonListItem(
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    }

    fun mapDetailsDtoToEntity(response: PokemonDetailsResponse): PokemonItemDetails {
        val englishEffects = response.effects.filter { it.language.name == DEFAULT_LANGUAGE_CODE }

        return PokemonItemDetails(
            id = response.id,
            name = response.name,
            cost = response.cost,
            imageUrl = response.images.default,
            flingPower = response.flingPower,
            attributes = response.attributes.map {
                PokemonItemAttribute(
                    name = it.name
                )
            },
            categories = PokemonItemCategory(
                name = response.categories.name
            ),
            effects = englishEffects.map {
                PokemonItemEffect(
                    effect = it.effect,
                    shortEffect = it.shortEffect
                )
            }
        )
    }

    private fun getImageUrl(name: String) = "$BASE_IMAGE_URL/$name.png"
}