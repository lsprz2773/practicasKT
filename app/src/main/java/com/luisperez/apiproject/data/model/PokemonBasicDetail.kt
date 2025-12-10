package com.luisperez.apiproject.data.model

import com.google.gson.annotations.SerializedName

data class PokemonBasicDetail (
    val id: Int,
    val name: String,

    val height: Int,
    val weight: Int,

    val sprites: SpriteContainer,

    val types: List<TypeSlot>,

    val abilities: List<AbilitySlot>,

    val stats: List<StatSlot>
)

data class SpriteContainer(
    @SerializedName("front_default") val pixelFront: String?,
    val other: OtherSprites?
)

data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontHd: String?
)

data class TypeSlot(
    val slot: Int,
    val type: NameUrl
)

data class AbilitySlot(
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val ability: NameUrl
)

data class StatSlot(
    @SerializedName("base_stat") val value: Int,
    val stat: NameUrl
)

data class NameUrl(
    val name: String,
    val url: String
)