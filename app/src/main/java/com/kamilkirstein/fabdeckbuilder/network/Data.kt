package com.kamilkirstein.fabdeckbuilder.network

data class Data(
    val banned: Boolean,
    val identifier: String,
    val image: String,
    val keywords: List<String>,
    val name: String,
    val printings: List<Printing>,
    val rarity: String,
    val stats: Any,
    val text: String,
    val totalSideboard: Int
)