package com.kamilkirstein.fabdeckbuilder.network


import com.squareup.moshi.Json
data class ListOfCards(
    @Json (name = "data") val cardsData: List<Data>,
    val links: Links,
    val meta: Meta
)