package com.kamilkirstein.fabdeckbuilder.network

data class Printing(
    val edition: Edition?,
    val id: Int = 0,
    val image: String?,
    val rarity: String?,
    val `set`: String?,
    val sku: Sku?
)