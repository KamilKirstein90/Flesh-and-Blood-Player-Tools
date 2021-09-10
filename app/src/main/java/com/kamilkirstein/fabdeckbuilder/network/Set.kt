package com.kamilkirstein.fabdeckbuilder.network

data class Set(
    val id: String?,
    val name: String?,
    val released: String?,
    val browseable: Boolean = false,
    val draftable: Boolean = false,
)