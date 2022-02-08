package com.kamilkirstein.fabdeckbuilder.network.data

data class Data(
    val banned: List<String>?,
    val identifier: String?,
    val image: String?,
    val keywords: List<String>?,
    val name: String?,
    val printings: List<Printing>?,
    val rarity: String?,
    val stats: Any?,
    val text: String?,
    val totalSideboard: Int = 0
)